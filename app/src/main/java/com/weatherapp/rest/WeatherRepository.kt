package com.weatherapp.rest

class WeatherRepository(private val apiHelper: ApiHelper) {

    suspend fun getWeatherData(q:String,appId:String) = apiHelper.getWeatherData(q,appId)

    suspend fun getWeatherDataDayList(lat:String,lon:String,cnt:String,appid:String) = apiHelper.getWeatherDataDayList(lat,lon,cnt,appid)
}

