package com.chilisoft.weatherx.common

object Constants {
    const val PREF_KEY_PREFERRED_UNIT = "preferred_unit"

    const val ICON_PLACEHOLDER = "{ICON_PLACEHOLDER}"
    const val BASE_DATA_URL = "https://api.openweathermap.org/data/2.5/"
    const val BASE_ICON_URL = "https://openweathermap.org/img/wn/$ICON_PLACEHOLDER@2x.png"
    const val BASE_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss"

    const val HOURLY_FORECAST_DATE_TIME_PATTERN = "EEE dd\nHH:mm"
    const val CURRENT_WEATHER_DATE_TIME_PATTERN = "EEEE, dd MMMM"

    const val CELSIUS = "°C" // For temperature in Celsius use units=metric
    const val FAHRENHEIT = "°F" // For temperature in Fahrenheit use units=imperial
}