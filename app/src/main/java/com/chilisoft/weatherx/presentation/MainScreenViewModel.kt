package com.chilisoft.weatherx.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chilisoft.weatherx.common.Resource
import com.chilisoft.weatherx.common.Units
import com.chilisoft.weatherx.domain.model.CurrentWeather
import com.chilisoft.weatherx.domain.model.HourlyForecast
import com.chilisoft.weatherx.domain.usecase.GetCurrentWeatherUseCase
import com.chilisoft.weatherx.domain.usecase.GetHourlyForecastUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainScreenViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getHourlyForecastUseCase: GetHourlyForecastUseCase,
) : ViewModel() {

    private val _stateCurrentWeather = MutableStateFlow<UiState<CurrentWeather>>(UiState.Loading)
    val stateCurrentWeather: StateFlow<UiState<CurrentWeather>> = _stateCurrentWeather

    private val _stateHourlyForecast = MutableStateFlow<UiState<List<HourlyForecast>>>(UiState.Loading)
    val stateHourlyForecast: StateFlow<UiState<List<HourlyForecast>>> = _stateHourlyForecast

    fun fetchWeather(city: String) {
        getHourlyForecastUseCase(city)
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

        getCurrentWeatherUseCase(city)
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


    fun getSelectedUnit(): Int {
        TODO("Not yet implemented")
    }

    fun getSettingItems(): List<Units> {
        return listOf(Units.Fahrenheit, Units.Celsius)
    }

    fun saveSelectedUnit() {
        TODO("Not yet implemented")
    }
}