package com.example.project9

data class CountryData(
    var name: String,
    var capital: String,
    var flags: Flag
)

data class Flag(
    var svg: String,
    var png: String
)