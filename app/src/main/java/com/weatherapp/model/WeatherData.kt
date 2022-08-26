package com.weatherapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class WeatherData(
    val base: String?=null,
    val clouds: Clouds?=null,
    val cod: Int?=null,
    val coord: Coord?=null,
    val dt: Int?=null,
    val id: Int?=null,
    val main: Main?=null,
    val name: String?=null,
    val sys: Sys?=null,
    val timezone: Int?=null,
    val visibility: Int?=null,
    val weather: List<Weather?>,
    val wind: Wind?=null
) : Parcelable

@Parcelize
data class Clouds(
    val all: Int?=null
) : Parcelable

@Parcelize
data class Coord(
    val lat: Double?=null,
    val lon: Double?=null
) : Parcelable
@Parcelize
data class Main(
    val feels_like: Double?=null,
    val humidity: Int?=null,
    val pressure: Int?=null,
    val temp: Double?=null,
    val temp_max: Double?=null,
    val temp_min: Double?=null
) : Parcelable

@Parcelize
data class Sys(
    val country: String?=null,
    val id: Int?=null,
    val sunrise: Int?=null,
    val sunset: Int?=null,
    val type: Int?=null
) : Parcelable

@Parcelize
data class Weather(
    val description: String?=null,
    val icon: String?=null,
    val id: Int?=null,
    val main: String?=null
) : Parcelable

@Parcelize
data class Wind(
    val deg: Int?=null,
    val speed: Double?=null
) : Parcelable