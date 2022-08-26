package com.weatherapp.rest

import com.weatherapp.BuildConfig
import com.weatherapp.utils.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitBuilder {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createApiServiceSession(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }


    private fun getHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request =
                chain.request().newBuilder()
                    .header("Content-Type", "application/json")
                    .build()
            chain.proceed(request)
        }
    }


    private fun provideHttpClient(): OkHttpClient {
        val timeout = 60 // Keeping timeout of 60 seconds
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient().newBuilder()
            .connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
            .readTimeout(timeout.toLong(), TimeUnit.SECONDS)
            .writeTimeout(timeout.toLong(), TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(Interceptor { chain ->

                val original = chain.request()
                getHeaderInterceptor()
                val requestBuilder = original.newBuilder()
                requestBuilder.header("Accept", "application/json")

                requestBuilder.method(original.method, original.body)
                val request = requestBuilder.build()
                val response = chain.proceed(request)
                val isSuccessful = response.isSuccessful
                val responseCode = response.code
                when {
                    isSuccessful -> {
                        val data = response.body!!.string()
                        return@Interceptor response.newBuilder()
                            .body(data.toResponseBody(response.body?.contentType())).build()
                    }
                }
                return@Interceptor response
            })
            .build()
    }
}