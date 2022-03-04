package com.chilisoft.weatherx.common

sealed class Units(val id: Int, val symbol: String, val fullName: String, val networkParam: String) {
    object Celsius : Units(1001, Constants.CELSIUS, "Celsius", "metric")
    object Fahrenheit : Units(1002, Constants.FAHRENHEIT, "Fahrenheit", "imperial")


}