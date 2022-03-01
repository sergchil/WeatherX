package com.chilisoft.weatherx

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.chilisoft.weatherx.databinding.ActivityMainBinding

// add readme
// add permission check
// add geocoder
// add mvvm layer
// add local and remote data sourses
// add animation
// handle no internet case
// add unit tests


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // make edge to edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleInsets()

        val adapter = HourlyForecastAdapter(mutableListOf())
        binding.weaklyForecast.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        binding.weaklyForecast.adapter = adapter

        // this will add the space to the last item as well
        // beter to extend the DividerItemDecoration and fix
        val d = DividerItemDecoration(this, LinearLayout.HORIZONTAL)
        ContextCompat.getDrawable(this, R.drawable.recycler_view_space_decoration)?.let {
            d.setDrawable(it)
        }

        binding.weaklyForecast.addItemDecoration(d)
        val list = mutableListOf<HourlyForecastDto>()

        repeat(20) {
            list.add(HourlyForecastDto())
        }
        adapter.updateDataSet(list)
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
