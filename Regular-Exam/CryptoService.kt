package com.example.crypto.service

import com.example.crypto.model.CryptoDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoService {

    @GET("markets?vs_currency=usd")
    fun getCryptoes(): Call<List<CryptoDetailResponse>>

    @GET("markets?vs_currency=usd")
    fun getCryptoByName(): Call<List<CryptoDetailResponse>>
}