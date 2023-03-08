package com.example.qoutesapplication

import retrofit2.Call
import retrofit2.http.GET

interface myinterface {
  @GET("api/quotes")
    fun getdatasoi():Call<List<myapidataItem>>
}