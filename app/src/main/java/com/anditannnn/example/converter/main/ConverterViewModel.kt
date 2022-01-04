package com.anditannnn.example.converter.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConverterViewModel : ViewModel() {

    private val _celsiusValue = MutableLiveData<String>()
    val celsiusValue: LiveData<String>
        get() = _celsiusValue

    private val _fahrenheitValue = MutableLiveData<String>()
    val fahrenheitValue: LiveData<String>
        get() = _fahrenheitValue

    private val _kelvinValue = MutableLiveData<String>()
    val kelvinValue: LiveData<String>
        get() = _kelvinValue

    init {
        _celsiusValue.value = ""
        _fahrenheitValue.value = ""
        _kelvinValue.value = ""
    }

    private fun convertFromCelsius(value: Double, typeTo: TEMPERATURE) : String {

        val convertResult = when (typeTo) {
            TEMPERATURE.FAHRENHEIT -> (1.8 * value) + 32
            TEMPERATURE.KELVIN -> value + 273.15
            else  -> 0
        }
        return String.format("%.2f", convertResult)
    }

    private fun convertFromFahrenheit(value: Double, typeTo: TEMPERATURE) : String {
        val convertResult = when (typeTo) {
            TEMPERATURE.CELSIUS -> (value-32)/1.8
            TEMPERATURE.KELVIN -> ((value + 459.67)*(5))/9
            else  -> 0
        }
        return String.format("%.2f", convertResult)
    }

    private fun convertFromKelvin(value: Double, typeTo: TEMPERATURE) : String {
        val convertResult = when (typeTo) {
            TEMPERATURE.CELSIUS -> value - 273.15
            TEMPERATURE.FAHRENHEIT -> ((value * 9)/5) - 459.67
            else  -> 0
        }
        return String.format("%.2f", convertResult)
    }

    fun convertCelsius(value: String) {
        _fahrenheitValue.value = convertFromCelsius(value.toDouble(), TEMPERATURE.FAHRENHEIT)
        _kelvinValue.value = convertFromCelsius(value.toDouble(), TEMPERATURE.KELVIN)
    }

    fun convertFahrenheit(value: String) {
        _celsiusValue.value = convertFromFahrenheit(value.toDouble(), TEMPERATURE.CELSIUS)
        _kelvinValue.value = convertFromFahrenheit(value.toDouble(), TEMPERATURE.KELVIN)
    }

    fun convertKelvin(value: String) {
        _celsiusValue.value = convertFromKelvin(value.toDouble(), TEMPERATURE.CELSIUS)
        _fahrenheitValue.value = convertFromKelvin(value.toDouble(), TEMPERATURE.FAHRENHEIT)
    }

    fun checkRegex(value: String) : Boolean {
        val regex = "[-+]?[0-9]*\\.?[0-9]+".toRegex()
        return regex.matches(value)
    }
}