package com.chilisoft.weatherx.data.mapper

import android.text.format.DateFormat
import com.chilisoft.weatherx.common.Constants.BASE_DATE_PATTERN
import com.chilisoft.weatherx.common.Constants.BASE_ICON_URL
import com.chilisoft.weatherx.common.Constants.HOURLY_FORECAST_DATE_TIME_PATTERN
import com.chilisoft.weatherx.common.Constants.ICON_PLACEHOLDER
import com.chilisoft.weatherx.common.TemperatureUnits
import com.chilisoft.weatherx.data.remote.dto.NetworkFiveDaysThreeHourForecast
import com.chilisoft.weatherx.domain.model.HourlyForecast
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round

fun NetworkFiveDaysThreeHourForecast.toHourlyForecast(unit: TemperatureUnits): List<HourlyForecast> {
    return list.map {
        HourlyForecast(
            weakDay = it.dtTxt.let {
                val date = SimpleDateFormat(BASE_DATE_PATTERN, Locale.US).parse(it)
                DateFormat.format(HOURLY_FORECAST_DATE_TIME_PATTERN, date).toString()
            },
            icon = BASE_ICON_URL.replace(ICON_PLACEHOLDER, it.weather.first().icon),
            temperature = round(it.main.temp).toInt().toString() + unit.temperatureSymbol
        )
    }
}

