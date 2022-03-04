package com.chilisoft.weatherx.data.remote.usecase

import android.content.SharedPreferences
import com.chilisoft.weatherx.common.Constants
import com.chilisoft.weatherx.common.Units
import com.chilisoft.weatherx.domain.usecase.GetPreferredUnitUseCase

class GetPreferredUnitUseCaseImpl(
    private val sharedPreferences: SharedPreferences
) : GetPreferredUnitUseCase {
    override fun invoke(): Units {
        val id = sharedPreferences.getInt(Constants.PREF_KEY_PREFERRED_UNIT, Units.Celsius.id)
        return when (id) {
            Units.Celsius.id -> Units.Celsius
            Units.Fahrenheit.id -> Units.Fahrenheit
            else -> {
                Units.Celsius
            }
        }
    }
}