package com.chilisoft.weatherx

import com.chilisoft.weatherx.network.WeatherService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<WeatherService> {
        val retrofit: Retrofit = get()
        retrofit.create(WeatherService::class.java)
    }


}