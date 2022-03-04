package com.chilisoft.weatherx.common

sealed class Units(val id: Int, val symbol: String, val fullName: String) {
    object Celsius : Units(1001, Constants.CELSIUS, "Celsius")
    object Fahrenheit : Units(1002, Constants.FAHRENHEIT, "Fahrenheit")


}