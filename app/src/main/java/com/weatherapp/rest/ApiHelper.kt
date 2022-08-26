package com.weatherapp.rest

class ApiHelper(private val apiService: ApiService) {

    suspend fun getWeatherData(q:String,appId:String) = apiService.getWeatherData(q,appId)

    suspend fun getWeatherDataDayList(lat:String,lon:String,cnt:String,appid:String) = apiService.getWeatherDataDayList(lat,lon,cnt,appid)
}