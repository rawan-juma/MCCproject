package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient private constructor() {
    val api: ApiInterface
        get() = retrofit.create(ApiInterface::class.java)

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
        private var apiClient: ApiClient? = null
        private lateinit var retrofit: Retrofit

        @get:Synchronized
        val instance: ApiClient?
            get() {
                if (apiClient == null) {
                    apiClient = ApiClient()
                }
                return apiClient
            }
    }

    init {
        retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}