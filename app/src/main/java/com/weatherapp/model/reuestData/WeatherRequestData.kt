package com.weatherapp.model.reuestData

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherRequestData(
    var city: String?=null,
    var currentWeather: Boolean?=null,
    var apiKey: String?=null,
    var lat: String?=null,
    var log: String?=null,
    var totalDays: String?=null,
) : Parcelable
