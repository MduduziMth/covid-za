package com.example.android.myapplication

import com.example.android.myapplication.model.Covid
import retrofit2.Call
import retrofit2.http.GET

interface CovidApi  {


    @GET("country/south-africa/status/confirmed")
    fun getCases(): Call<List<Covid>>

    @GET("country/south-africa/status/recovered")
    fun getRecoveries(): Call<List<Covid>>

    @GET("country/south-africa/status/deaths")
    fun getDeaths(): Call<List<Covid>>


}