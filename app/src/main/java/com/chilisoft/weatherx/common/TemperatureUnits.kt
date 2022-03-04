package com.chilisoft.weatherx.common

sealed class TemperatureUnits(
    val id: Int,
    val temperatureSymbol: String,
    val unitName: String,
    val windMetricsSymbol: String,
    val networkParam: String
) {
    object Celsius : TemperatureUnits(
        1001,
        Constants.CELSIUS,
        "Celsius",
        "m/s",
        "metric"
    )

    object Fahrenheit : TemperatureUnits(
        1002,
        Constants.FAHRENHEIT,
        "Fahrenheit",
        "m/h",
        "imperial"
    )
}