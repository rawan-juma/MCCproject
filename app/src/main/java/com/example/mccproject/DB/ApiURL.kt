package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiURL private constructor() {
    val api: ApiFunctionsInter
        get() = retrofit.create(ApiFunctionsInter::class.java)

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
        private var apiURL: ApiURL? = null
        private lateinit var retrofit: Retrofit

        @get:Synchronized
        val instance: ApiURL?
            get() {
                if (apiURL == null) {
                    apiURL = ApiURL()
                }
                return apiURL
            }
    }

    init {
        retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}