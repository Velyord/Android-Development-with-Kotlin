package com.example.project9

import retrofit2.Call
import retrofit2.http.GET

interface CountryInterface {
    @GET("all")
    fun getCountries(): Call<List<CountryData>>
}