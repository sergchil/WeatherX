package com.chilisoft.weatherx.data.remote

import com.chilisoft.weatherx.data.remote.dto.NetworkCurrentWeatherForecast
import com.chilisoft.weatherx.data.remote.dto.NetworkFiveDaysThreeHourForecast
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") unit: String,
        @Query("appId") id: String = "8a1a8f1412d67f305b28b4c90948e1c2"
    ): NetworkCurrentWeatherForecast

    @GET("forecast")
    suspend fun getHourlyForecast(
        @Query("q") city: String,
        @Query("units") unit: String,
        @Query("appId") id: String = "8a1a8f1412d67f305b28b4c90948e1c2"
    ): NetworkFiveDaysThreeHourForecast
}
