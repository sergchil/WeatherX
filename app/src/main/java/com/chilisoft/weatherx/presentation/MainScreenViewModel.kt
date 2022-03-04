package com.chilisoft.weatherx.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chilisoft.weatherx.common.Resource
import com.chilisoft.weatherx.common.Units
import com.chilisoft.weatherx.domain.model.CurrentWeather
import com.chilisoft.weatherx.domain.model.HourlyForecast
import com.chilisoft.weatherx.domain.usecase.GetCurrentWeatherUseCase
import com.chilisoft.weatherx.domain.usecase.GetHourlyForecastUseCase
import com.chilisoft.weatherx.domain.usecase.GetPreferredUnitUseCase
import com.chilisoft.weatherx.domain.usecase.SavePreferredUnitUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MainScreenViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getHourlyForecastUseCase: GetHourlyForecastUseCase,
    private val getPreferredUnitUseCase: GetPreferredUnitUseCase,
    private val savePreferredUnitUseCase: SavePreferredUnitUseCase
) : ViewModel() {

    private val _stateCurrentWeather = MutableStateFlow<UiState<CurrentWeather>>(UiState.Loading)
    val stateCurrentWeather: StateFlow<UiState<CurrentWeather>> = _stateCurrentWeather

    private val _stateHourlyForecast = MutableStateFlow<UiState<List<HourlyForecast>>>(UiState.Loading)
    val stateHourlyForecast: StateFlow<UiState<List<HourlyForecast>>> = _stateHourlyForecast

    fun fetchWeather(city: String, unit: Units) {
        getHourlyForecastUseCase(city, unit)
            .onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _stateHourlyForecast.emit(UiState.Loading)
                    }
                    is Resource.Success -> {
                        _stateHourlyForecast.emit(UiState.Success(result.data))
                    }
                    is Resource.Error -> {
                        _stateHourlyForecast.emit(UiState.Error(result.message ?: "An unexpected error occurred"))
                    }
                }
            }
            .launchIn(viewModelScope)

        getCurrentWeatherUseCase(city, unit)
            .onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _stateCurrentWeather.emit(UiState.Loading)
                    }
                    is Resource.Success -> {
                        _stateCurrentWeather.emit(UiState.Success(result.data))
                    }
                    is Resource.Error -> {
                        _stateCurrentWeather.emit(UiState.Error(result.message ?: "An unexpected error occurred"))
                    }
                }

            }
            .launchIn(viewModelScope)
    }

    fun getPreferredUnit(): Units {
        return getPreferredUnitUseCase()
    }

    fun getSettingItems(): List<Units> {
        return listOf(Units.Fahrenheit, Units.Celsius)
    }

    fun savePreferredUnit(units: Units) {
        savePreferredUnitUseCase(units)
    }
}