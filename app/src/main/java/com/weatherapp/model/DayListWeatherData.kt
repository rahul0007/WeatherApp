package com.weatherapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DayListWeatherData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherData>,
    val message: Int
) : Parcelable

@Parcelize
data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
) : Parcelable
