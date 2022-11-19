package com.example.project9

import retrofit2.Call

class CountryRepository constructor(
    private val countryInterface: CountryInterface
){
    fun getCountries(): Call<List<CountryData>>? {
        return try {
            countryInterface.getCountries()
        } catch (e: Exception) {
            // log error
            // show message to user
            return null
        }
    }
}