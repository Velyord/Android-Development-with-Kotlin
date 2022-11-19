package net.gostartups.myapplication.service

import net.gostartups.myapplication.model.CountryDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {

    @GET("all")
    fun getCountries(): Call<List<CountryDetailResponse>>

    @GET("name/{name}")
    fun getCountryByName(@Path("name") name: String): Call<List<CountryDetailResponse>>
}