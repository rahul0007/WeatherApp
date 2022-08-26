package com.weatherapp.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.weatherapp.model.ErrorResponse
import com.weatherapp.model.WeatherData
import retrofit2.HttpException
import kotlin.math.roundToInt

fun hideKeyboard(activity: FragmentActivity?, view: View?) {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun showSnackBar(rootView: View, message: String, actionString: String) {
    val snack = Snackbar.make(rootView, message, Snackbar.LENGTH_INDEFINITE)
    snack.setAction(actionString) {
        snack.dismiss()
    }
    snack.show()
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


fun View.showKeyboard() {
    this.requestFocus()
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.let {
            val gson = Gson()
            val adapter: TypeAdapter<ErrorResponse> = gson.getAdapter(ErrorResponse::class.java)
            adapter.fromJson(it.string())
        }
    } catch (exception: Exception) {
        ErrorResponse()
    }
}

fun getTemperature(weatherData: WeatherData): String {
    return "" + (weatherData.main!!.temp!! - 273.15).roundToInt() + " �C"
}

fun getWindSpeed(weatherData: WeatherData): String {
    return "" + weatherData.wind!!.speed +"mps"
}

fun getTemperatureC(weatherData: WeatherData): String {
    val b: Double = (weatherData.main!!.temp!! - 273.15) * 9 / 5 + 32
    val r = b.toString()
    return "$r°F"
}
fun getTemperatureF(weatherData: WeatherData): String {
    val b: Double = (weatherData.main!!.temp!! - 273.15) - 32
    val c = b * 5 / 9
    val r = c.toString()
    return "$r°C"
}


