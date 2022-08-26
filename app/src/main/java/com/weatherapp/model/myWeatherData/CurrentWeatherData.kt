package com.weatherapp.model.myWeatherData

import android.os.Parcelable
import com.weatherapp.model.City
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentWeatherData(
    val Temperature: String,
    val WindSpeed: String,
    val TemperatureC: String,
    val TemperatureF: String,
) : Parcelable