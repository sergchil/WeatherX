package com.chilisoft.weatherx.domain.usecase

import com.chilisoft.weatherx.common.Resource
import com.chilisoft.weatherx.common.TemperatureUnits
import com.chilisoft.weatherx.domain.model.CurrentWeather
import kotlinx.coroutines.flow.Flow

interface GetCurrentWeatherUseCase {
    operator fun invoke(city: String, unit: TemperatureUnits): Flow<Resource<CurrentWeather>>
}