package com.chilisoft.weatherx.data.remote.usecase

import android.content.SharedPreferences
import com.chilisoft.weatherx.common.Constants
import com.chilisoft.weatherx.common.TemperatureUnits
import com.chilisoft.weatherx.domain.usecase.GetPreferredUnitUseCase

class GetPreferredUnitUseCaseImpl(
    private val sharedPreferences: SharedPreferences
) : GetPreferredUnitUseCase {
    override fun invoke(): TemperatureUnits {
        val id = sharedPreferences.getInt(Constants.PREF_KEY_PREFERRED_UNIT, TemperatureUnits.Celsius.id)
        return when (id) {
            TemperatureUnits.Celsius.id -> TemperatureUnits.Celsius
            TemperatureUnits.Fahrenheit.id -> TemperatureUnits.Fahrenheit
            else -> {
                TemperatureUnits.Celsius
            }
        }
    }
}