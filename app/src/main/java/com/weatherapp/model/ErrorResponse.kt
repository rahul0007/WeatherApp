package com.weatherapp.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("message")
    val message: String,
    @SerializedName("statuscode")
    val statuscode: Int
){
    constructor():this(0,"Error occurred!!",0)
}
