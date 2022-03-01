package com.chilisoft.weatherx.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface WeatherService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appId") id: String = "8a1a8f1412d67f305b28b4c90948e1c2"
    ): CurrentWeatherForecastModel
}
