package com.chilisoft.weatherx.domain.usecase

import com.chilisoft.weatherx.common.Units

interface SavePreferredUnitUseCase {
    operator fun invoke(unit: Units)
}