package com.chilisoft.weatherx.data.mapper

import com.chilisoft.weatherx.common.Constants
import com.chilisoft.weatherx.common.Constants.BASE_ICON_URL
import com.chilisoft.weatherx.common.TemperatureUnits
import com.chilisoft.weatherx.data.remote.dto.NetworkCurrentWeatherForecast
import com.chilisoft.weatherx.domain.model.CurrentWeather
import kotlin.math.round

fun NetworkCurrentWeatherForecast.toCurrentWeather(unit: TemperatureUnits): CurrentWeather {
    return CurrentWeather(
        weakDay = dt * 1000L, // convert epoch to instant
        city = name,
        country = sys.country,
        temperature = round(main.temp).toInt().toString() + unit.temperatureSymbol,
        humidity = main.humidity.toString() + "%",
        wind = round(wind.speed).toInt().toString() + unit.windMetricsSymbol,
        visibility = visibility.toString() + "m",
        realFeel = round(main.feelsLike).toInt().toString() + unit.temperatureSymbol,
        icon = BASE_ICON_URL.replace(Constants.ICON_PLACEHOLDER, weather.first().icon),
    )
}