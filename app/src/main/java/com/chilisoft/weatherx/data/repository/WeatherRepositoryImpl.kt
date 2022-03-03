package com.chilisoft.weatherx.domain.repository

import com.chilisoft.weatherx.data.remote.WeatherService
import com.chilisoft.weatherx.data.remote.dto.NetworkCurrentWeatherForecast
import com.chilisoft.weatherx.data.remote.dto.NetworkFiveDaysThreeHourForecast

class WeatherRepositoryImpl(
    private val api: WeatherService
) : WeatherRepository {

    override suspend fun getHourlyForecast(city: String): NetworkFiveDaysThreeHourForecast {
        return api.getHourlyForecast(city)
    }

    override suspend fun getCurrentWeather(city: String): NetworkCurrentWeatherForecast {
        return api.getCurrentWeather(city)
    }
}