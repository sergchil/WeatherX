package com.chilisoft.weatherx.data.remote.usecase

import com.chilisoft.weatherx.common.Resource
import com.chilisoft.weatherx.data.mapper.toCurrentWeather
import com.chilisoft.weatherx.domain.model.CurrentWeather
import com.chilisoft.weatherx.domain.repository.WeatherRepository
import com.chilisoft.weatherx.domain.usecase.GetCurrentWeatherUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCurrentWeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : GetCurrentWeatherUseCase {

    override fun invoke(city: String): Flow<Resource<CurrentWeather>> = flow {
        try {
            emit(Resource.Loading())
            val weather = weatherRepository.getCurrentWeather(city).toCurrentWeather()
            emit(Resource.Success(weather))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}