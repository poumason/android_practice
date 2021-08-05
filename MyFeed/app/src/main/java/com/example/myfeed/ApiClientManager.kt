package com.example.myfeed

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ApiClientManager private constructor() {
    private val retrofit: Retrofit
    private val okHttpClient: OkHttpClient

    private val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.i("interceptor msg", message)
        }
    })

    init {
        okHttpClient = OkHttpClient().newBuilder().addInterceptor(logging).build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    companion object {
        private val manager = ApiClientManager()
        val client: Retrofit
            get() = manager.retrofit
    }
}


interface ApiService {
    @GET("/posts")
    fun index(): Call<List<Posts>>
}