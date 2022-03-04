package com.chilisoft.weatherx.domain.usecase

import com.chilisoft.weatherx.common.TemperatureUnits

interface SavePreferredUnitUseCase {
    operator fun invoke(unit: TemperatureUnits)
}