package com.chilisoft.weatherx.domain.usecase

import com.chilisoft.weatherx.common.TemperatureUnits

interface GetPreferredUnitUseCase {
    operator fun invoke(): TemperatureUnits
}