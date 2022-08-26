package com.weatherapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.weatherapp.utils.UNKNOWN_CLASS_NAME
import com.weatherapp.rest.ApiHelper
import com.weatherapp.rest.WeatherRepository

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(WeatherRepository(apiHelper)) as T
        }
        throw IllegalArgumentException(UNKNOWN_CLASS_NAME)
    }

}