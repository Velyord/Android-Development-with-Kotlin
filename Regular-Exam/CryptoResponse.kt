package com.example.crypto.model

data class CryptoResponse(
    var name: String,
    var symbol: String,
    var price: Double,
    var image: String
)