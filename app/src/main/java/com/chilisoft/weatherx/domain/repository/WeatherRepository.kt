package com.chilisoft.weatherx.domain.repository

import com.chilisoft.weatherx.data.remote.dto.NetworkCurrentWeatherForecast
import com.chilisoft.weatherx.data.remote.dto.NetworkFiveDaysThreeHourForecast

interface WeatherRepository {

    suspend fun getHourlyForecast(city: String): NetworkFiveDaysThreeHourForecast

    suspend fun getCurrentWeather(city: String): NetworkCurrentWeatherForecast
}