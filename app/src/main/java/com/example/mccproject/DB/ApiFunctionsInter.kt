package com.example.myapplication


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiFunctionsInter {

    @GET("everything")
    fun getSpecificData(
        @Query("q") query: String?,
        @Query("from") query2: String?,
        @Query("to") query3: String?,
        @Query("apiKey") apiKey: String?,
        @Query("language") language:String,
        @Query("sortBy") publishedAt:String,
        @Query("pageSize") pageSize:Int,

    ): Call<HeadlinesModel?>?

    @GET("everything")
    fun getUrgentData(

       @Query("q") query: String?,
        @Query("apiKey") apiKey: String?,
       @Query("language") language:String,
       @Query("sortBy") publishedAt:String,
       @Query("pageSize") pageSize:Int
    ): Call<HeadlinesModel?>?

}