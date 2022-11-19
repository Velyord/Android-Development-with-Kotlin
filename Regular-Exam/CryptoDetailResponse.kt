package com.example.crypto.model

data class CryptoDetailResponse(
    var name: String?,
    var symbol: String?,
    var image: String?,
    var price: Double?,
    var marketCap: Int?,
    var high24h: Double?,
    var priceChangePercentage24h: Double?,
    var marketCapChangePercentage24h: Double?,
    var low24h: Double?
)