package com.weatherapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.weatherapp.rest.WeatherRepository
import com.weatherapp.rest.Resource
import com.weatherapp.utils.api_key
import com.weatherapp.utils.convertErrorBody
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.io.IOException

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    fun getWeatherData(cityName:String,apiKey:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = weatherRepository.getWeatherData(cityName,apiKey)))
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> emit(Resource.networkError(data = null))
                is HttpException -> {
                    val errorResponse = convertErrorBody(throwable)
                    emit(
                        Resource.error(
                            data = null,
                            message = errorResponse?.message ?: "Error Occurred!"
                        )
                    )
                }
                else -> {
                    emit(Resource.error(data = null, message = "Error Occurred!"))
                }
            }
        }
    }

    fun getWeatherDataDayList(lat:String,lon:String,dayCount:String,apiKey: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = weatherRepository.getWeatherDataDayList(lat,lon,dayCount,api_key)))
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> emit(Resource.networkError(data = null))
                is HttpException -> {
                    val errorResponse = convertErrorBody(throwable)
                    emit(
                        Resource.error(
                            data = null,
                            message = errorResponse?.message ?: "Error Occurred!"
                        )
                    )
                }
                else -> {
                    emit(Resource.error(data = null, message = "Error Occurred!"))
                }
            }
        }
    }

}