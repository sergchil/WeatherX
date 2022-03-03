package com.chilisoft.weatherx.data.remote.usecase

import com.chilisoft.weatherx.common.Resource
import com.chilisoft.weatherx.data.mapper.toHourlyForecast
import com.chilisoft.weatherx.domain.model.HourlyForecast
import com.chilisoft.weatherx.domain.repository.WeatherRepository
import com.chilisoft.weatherx.domain.usecase.GetHourlyForecastUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetHourlyForecastUseCaseImpl(private val weatherRepository: WeatherRepository) : GetHourlyForecastUseCase {

    override fun invoke(city: String): Flow<Resource<List<HourlyForecast>>> = flow {
        try {
            emit(Resource.Loading())
            val weather = weatherRepository.getHourlyForecast(city).toHourlyForecast()
            emit(Resource.Success(weather))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}