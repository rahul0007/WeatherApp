package com.weatherapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.weatherapp.model.DayListWeatherData
import com.weatherapp.model.myWeatherData.CurrentWeatherData
import com.weatherapp.model.reuestData.WeatherRequestData
import com.weatherapp.utils.SUCCESS_CURRENT_WEATHER_CODE
import com.weatherapp.utils.SUCCESS_LAST_7DAYS


class TestingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        getCurrentWeather()
    }

    //    current day
    private fun getCurrentWeather() {
        val intent = Intent(this, WeatherActivity::class.java)
        val weatherRequestDate = WeatherRequestData(
            city = "Ahmedabad",
            currentWeather = true,
            apiKey = "ae1c4977a943a50eaa7da25e6258d8b2"
        )
        intent.putExtra("weatherRequestDate", weatherRequestDate)
        startForResult.launch(intent)
    }

    // last 7 days data
    private fun openActivityForResultLast7Days() {
        val intent = Intent(this, WeatherActivity::class.java)
        val weatherRequestDate = WeatherRequestData(
            city = "Ahmedabad",
            currentWeather = false,
            apiKey = "ae1c4977a943a50eaa7da25e6258d8b2",
            lat = "37",
            log = "137",
            totalDays = "7"
        )
        intent.putExtra("weatherRequestDate", weatherRequestDate)
        startForResult.launch(intent)
    }

    val startForResult =
        registerForActivityResult(StartActivityForResult()) { result: ActivityResult ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                }
                SUCCESS_CURRENT_WEATHER_CODE -> {
                    val intent = result.data!!.extras!!.get("WeatherResult") as CurrentWeatherData
                    Log.e("intent.Temperature", "intent.Temperature" + intent.Temperature);
                }
                SUCCESS_LAST_7DAYS -> {
                    val last7Days =
                        result.data!!.extras!!.get("WeatherResult") as DayListWeatherData
                    Log.e("last7Days", "last7Days" + last7Days.city)
                }
            }
        }
}