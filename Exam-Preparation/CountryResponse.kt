package net.gostartups.myapplication.model

data class CountryResponse(
    var name: String,
    var capital: String,
    var flags: Flag
)

data class Flag(
    var svg: String,
    var png: String
)