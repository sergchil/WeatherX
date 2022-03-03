package com.chilisoft.weatherx.data.mapper

import com.chilisoft.weatherx.data.remote.dto.NetworkFiveDaysThreeHourForecast
import com.chilisoft.weatherx.domain.model.HourlyForecast

fun NetworkFiveDaysThreeHourForecast.toHourlyForecast(): List<HourlyForecast> {
    return list.map {
        HourlyForecast(
            weakDay = it.dtTxt,
            icon = it.weather.first().icon,
            temperature = it.weather.first().icon
        )
    }
}