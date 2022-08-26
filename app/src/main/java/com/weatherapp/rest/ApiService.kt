package com.weatherapp.rest

import com.weatherapp.model.DayListWeatherData
import com.weatherapp.model.WeatherData
import retrofit2.http.*


interface ApiService {

    @GET("data/2.5/weather")
    suspend fun getWeatherData(@Query("q") q:String,@Query("APPID") appId:String): WeatherData

    @GET("data/2.5/forecast")
    suspend fun getWeatherDataDayList(@Query("lat") lat:String,@Query("lon") lon:String,@Query("cnt") cnt:String,@Query("appid") appid:String): DayListWeatherData
}

