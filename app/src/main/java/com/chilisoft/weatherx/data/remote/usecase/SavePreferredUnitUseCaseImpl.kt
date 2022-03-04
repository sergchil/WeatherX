package com.chilisoft.weatherx.data.remote.usecase

import android.content.SharedPreferences
import com.chilisoft.weatherx.common.Constants
import com.chilisoft.weatherx.common.TemperatureUnits
import com.chilisoft.weatherx.domain.usecase.SavePreferredUnitUseCase

class SavePreferredUnitUseCaseImpl(
    private val sharedPreferences: SharedPreferences
) : SavePreferredUnitUseCase {
    override fun invoke(unit: TemperatureUnits) {
        sharedPreferences
            .edit()
            .putInt(Constants.PREF_KEY_PREFERRED_UNIT, unit.id)
            .apply()
    }
}