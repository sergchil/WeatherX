package com.chilisoft.weatherx.presentation

import android.os.Bundle
import android.text.format.DateFormat
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import coil.load
import com.chilisoft.weatherx.R
import com.chilisoft.weatherx.common.Constants
import com.chilisoft.weatherx.databinding.ActivityMainBinding
import com.chilisoft.weatherx.domain.model.CurrentWeather
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.*

// add readme
// add permission check
// add geocoder
// add animation
// handle no internet case
// add unit tests


class MainScreenActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding
    private lateinit var hourlyForecastAdapter: HourlyForecastAdapter
    private val mainScreenViewModel: MainScreenViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // make edge to edge
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleInsets()
        setupHourlyRecyclerView()
        registerObservables()
        mainScreenViewModel.fetchWeather("yerevan")
    }

    private fun setupHourlyRecyclerView() {
        hourlyForecastAdapter = HourlyForecastAdapter(mutableListOf())
        binding.weaklyForecast.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        binding.weaklyForecast.adapter = hourlyForecastAdapter

        // this will add the space to the last item as well
        // better to extend the DividerItemDecoration and fix
        ContextCompat.getDrawable(this, R.drawable.recycler_view_space_decoration)?.let {
            val decor = DividerItemDecoration(this, LinearLayout.HORIZONTAL)
            decor.setDrawable(it)
            binding.weaklyForecast.addItemDecoration(decor)
        }
    }

    private fun registerObservables() {
        lifecycleScope.launch {
            launch { collectCurrentWeather() }
            launch { collectHourlyForecast() }
        }
    }

    private suspend fun collectCurrentWeather() {
        mainScreenViewModel.stateCurrentWeather
            .flowWithLifecycle(lifecycle)
            .collect { uiState ->
                when (uiState) {
                    is UiState.Loading -> {

                    }

                    is UiState.Success -> {
                        setupCurrentWeather(uiState.data ?: return@collect)
                    }

                    is UiState.Error -> {

                    }

                    else -> {}
                }
            }
    }

    private fun setupCurrentWeather(data: CurrentWeather) {
        val date = Date(data.weakDay)
        binding.todaysDate.text = DateFormat.format(Constants.CURRENT_WEATHER_DATE_TIME_PATTERN, date).toString()

        binding.cityName.text = data.city
        binding.countryName.text = Locale("EN", data.country).displayCountry
        binding.weatherIcon.load(data.icon)
        binding.temperature.text = data.temperature

        binding.humidity.title.text = "Humidity"
        binding.humidity.detail.text = data.humidity

        binding.visibility.title.text = "Visibility"
        binding.visibility.detail.text = data.visibility

        binding.wind.title.text = "Wind"
        binding.wind.detail.text = data.wind

        binding.realFeel.title.text = "Real Feel"
        binding.realFeel.detail.text = data.realFeel

    }

    private suspend fun collectHourlyForecast() {
        mainScreenViewModel.stateHourlyForecast
            .flowWithLifecycle(lifecycle)
            .collect { uiState ->

                when (uiState) {
                    is UiState.Loading -> {

                    }

                    is UiState.Success -> {
                        hourlyForecastAdapter.updateDataSet(uiState.data?.toMutableList())
                    }

                    is UiState.Error -> {

                    }

                    else -> {}
                }
            }
    }

    private fun handleInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainContent) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<FrameLayout.LayoutParams> {
                leftMargin = insets.left
                bottomMargin = insets.bottom
                rightMargin = insets.right
                topMargin = insets.top
            }

            WindowInsetsCompat.CONSUMED
        }
    }
}
