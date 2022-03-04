package com.chilisoft.weatherx.domain.usecase

import com.chilisoft.weatherx.common.Units

interface GetPreferredUnitUseCase {
    operator fun invoke(): Units
}