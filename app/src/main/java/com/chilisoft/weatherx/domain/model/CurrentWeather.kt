package com.chilisoft.weatherx.domain.model

data class CurrentWeather(
    val weakDay: String,
    val city: String,
    val country: String,
    val temperature: String,
    val humidity: String,
    val wind: String,
    val visibility: String,
    val realFeel: String,
    val icon: String
)
