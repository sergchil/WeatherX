package com.chilisoft.weatherx.data.mapper

import com.chilisoft.weatherx.common.Constants
import com.chilisoft.weatherx.common.Constants.BASE_ICON_URL
import com.chilisoft.weatherx.data.remote.dto.NetworkCurrentWeatherForecast
import com.chilisoft.weatherx.domain.model.CurrentWeather

fun NetworkCurrentWeatherForecast.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        weakDay = dt.toString(),
        city = name,
        country = sys.country,
        temperature = main.temp.toString(),
        humidity = main.humidity.toString(),
        wind = wind.speed.toString(),
        visibility = visibility.toString(),
        realFeel = main.feelsLike.toString(),
        icon = BASE_ICON_URL.replace(Constants.ICON_PLACEHOLDER, weather.first().icon),
    )
}