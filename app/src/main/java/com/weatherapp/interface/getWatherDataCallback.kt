package com.weatherapp.`interface`

import com.weatherapp.model.myWeatherData.CurrentWeatherData

interface WeatherDataCallback {
    fun getCurrentWeather(currentWeatherData: CurrentWeatherData)
    fun getLast7DaysWeather()
}