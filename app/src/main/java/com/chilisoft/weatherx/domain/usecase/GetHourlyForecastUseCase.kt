package com.chilisoft.weatherx.domain.usecase

import com.chilisoft.weatherx.common.Resource
import com.chilisoft.weatherx.domain.model.HourlyForecast
import kotlinx.coroutines.flow.Flow

interface GetHourlyForecastUseCase {
    operator fun invoke(city: String): Flow<Resource<List<HourlyForecast>>>
}