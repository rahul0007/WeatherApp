package com.weatherapp

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.weatherapp.*
import com.weatherapp.`interface`.WeatherDataCallback
import com.weatherapp.model.myWeatherData.CurrentWeatherData
import com.weatherapp.model.reuestData.WeatherRequestData
import com.weatherapp.rest.ApiHelper
import com.weatherapp.rest.RetrofitBuilder
import com.weatherapp.rest.Status
import com.weatherapp.utils.*
import com.weatherapp.viewModel.ViewModelFactory
import com.weatherapp.viewModel.WeatherViewModel
import java.util.ArrayList


class WeatherActivity : AppCompatActivity() {
    // Intetialize the viewmodel object
    lateinit var weatherRequestData: WeatherRequestData
    private val mViewModel: WeatherViewModel by viewModels {
        ViewModelFactory(ApiHelper(RetrofitBuilder.createApiServiceSession()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherRequestData = intent.extras!!.get("weatherRequestDate") as WeatherRequestData
        if (weatherRequestData.currentWeather == true) {
            weatherRequestData.city?.let { weatherRequestData.apiKey?.let { it1 ->
                getWeatherData(it,
                    it1
                )
            } }

        } else {
            getWeatherDataDayList(
                weatherRequestData.apiKey!!,
                weatherRequestData.lat!!,
                weatherRequestData.log!!,
                weatherRequestData.totalDays!!
            )
        }

    }


    /*
    * This method is help for getting the weather data
    * @PARAM need to pass city name.
    * */
    private fun getWeatherData(cityName: String, apiKey: String) {
        mViewModel.getWeatherData(cityName, apiKey).observe(this) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {

                        it.data?.let {
                            //Get temperature
                            val currentWeatherData = CurrentWeatherData(
                                getTemperature(it), getWindSpeed(it), getTemperatureC(it),
                                getTemperatureF(it)
                            )

                            val resultIntent = Intent()
                            resultIntent.putExtra("WeatherResult", currentWeatherData)
                            setResult(SUCCESS_CURRENT_WEATHER_CODE, resultIntent)
                            finish()
                        }

                    }
                    Status.ERROR -> {
                        val resultIntent = Intent()
                        setResult(WEATHER_ERROR_CODE, resultIntent)
                        finish()
                    }
                    Status.LOADING -> {

                    }
                    Status.NETWORK_ERROR -> {
                        val resultIntent = Intent()
                        setResult(WEATHER_ERROR_CODE, resultIntent)
                        finish()
                    }
                }
            }
        }
    }


    /*
    * getting weather for a specified future timeframe (1-7) days.
    *35,139,7
    * @PARAM need to pass latitude and longitude
    * */
    private fun getWeatherDataDayList(apiKey: String, lat: String, lng: String, totalDay: String) {
        mViewModel.getWeatherDataDayList(lat, lng, totalDay, apiKey).observe(this) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        try {
                            val resultIntent = Intent()
                            resultIntent.putExtra(
                                "WeatherResult",
                                it.data
                            )
                            setResult(SUCCESS_LAST_7DAYS, resultIntent)
                            finish()
                        } catch (e: Exception) {

                            e.printStackTrace()
                        }
                    }
                    Status.ERROR -> {
                        val resultIntent = Intent()
                        setResult(WEATHER_ERROR_CODE, resultIntent)
                        finish()
                    }
                    Status.LOADING -> {

                    }
                    Status.NETWORK_ERROR -> {
                        val resultIntent = Intent()
                        setResult(WEATHER_ERROR_CODE, resultIntent)
                        finish()
                    }
                }
            }
        }
    }


}