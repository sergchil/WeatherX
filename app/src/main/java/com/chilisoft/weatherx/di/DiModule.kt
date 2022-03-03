package com.chilisoft.weatherx.di

import com.chilisoft.weatherx.common.Constants
import com.chilisoft.weatherx.data.remote.WeatherService
import com.chilisoft.weatherx.data.remote.usecase.GetCurrentWeatherUseCaseImpl
import com.chilisoft.weatherx.data.remote.usecase.GetHourlyForecastUseCaseImpl
import com.chilisoft.weatherx.domain.repository.WeatherRepository
import com.chilisoft.weatherx.domain.repository.WeatherRepositoryImpl
import com.chilisoft.weatherx.domain.usecase.GetCurrentWeatherUseCase
import com.chilisoft.weatherx.domain.usecase.GetHourlyForecastUseCase
import com.chilisoft.weatherx.presentation.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val AppModule = module {

    viewModel { MainScreenViewModel(get(), get()) }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_DATA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<WeatherService> { get<Retrofit>().create(WeatherService::class.java) }

    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    single<GetCurrentWeatherUseCase> { GetCurrentWeatherUseCaseImpl(get()) }
    single<GetHourlyForecastUseCase> { GetHourlyForecastUseCaseImpl(get()) }

}