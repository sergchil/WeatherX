package com.chilisoft.weatherx.presentation

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.chilisoft.weatherx.R
import com.chilisoft.weatherx.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

// add readme
// add permission check
// add geocoder
// add mvvm layer
// add local and remote data sourses
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

        hourlyForecastAdapter = HourlyForecastAdapter(mutableListOf())
        binding.weaklyForecast.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        binding.weaklyForecast.adapter = hourlyForecastAdapter

        // this will add the space to the last item as well
        // better to extend the DividerItemDecoration and fix
        val d = DividerItemDecoration(this, LinearLayout.HORIZONTAL)
        ContextCompat.getDrawable(this, R.drawable.recycler_view_space_decoration)?.let {
            d.setDrawable(it)
        }

        binding.weaklyForecast.addItemDecoration(d)

        registerObservables()
        mainScreenViewModel.fetchWeather("yerevan")
    }

    private fun registerObservables() {
        // Start a coroutine in the lifecycle scope
        lifecycleScope.launch {
            launch { collectCurrentWeather() }
            launch { collectHourlyForecast() }
        }
    }

    private suspend fun collectCurrentWeather() {
        mainScreenViewModel.stateCurrentWeather
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .collect { uiState ->
                when (uiState) {
                    is UiState.Loading -> {

                    }

                    is UiState.Success -> {

                    }

                    is UiState.Error -> {

                    }

                    else -> {}
                }
            }
    }

    private suspend fun collectHourlyForecast() {
        mainScreenViewModel.stateHourlyForecast
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
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
