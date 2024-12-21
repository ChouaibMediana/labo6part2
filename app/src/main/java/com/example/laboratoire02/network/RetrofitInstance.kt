package com.example.laboratoire02.network

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
private const val BASE_URL = "https://newsapi.org"
class RetrofitInstance {
    companion object {
        private val retrofitInstance by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val retrofitService by lazy {
            retrofitInstance.create(NewsApiService::class.java)
        }

        init {
            Log.d("RetrofitInstance", "Retrofit instance created with base URL: $BASE_URL")
        }
    }
}