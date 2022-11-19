package net.gostartups.myapplication.model

data class CountryDetailResponse(
    var flags: Flag,
    var name: String?,
    var capital: String?,
    var alpha2Code: String?,
    var callingCodes: List<String>,
    var subregion: String?,
    var population: Int?,
    var area: Float,
    var currencies: List<Currency>?,
    var languages: List<Language>?
)

data class Currency(
    var code: String?,
    var name: String?
)

data class Language(
    var name: String?
)