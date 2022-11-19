package net.gostartups.myapplication.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryDetails(
    @PrimaryKey
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "capital") var capital: String,
    @ColumnInfo(name = "flag") var flag: String,
    @ColumnInfo(name = "alpha2_code") var alpha2Code: String,
    @ColumnInfo(name = "calling_code") var callingCode: String,
    @ColumnInfo(name = "subregion") var subregion: String,
    @ColumnInfo(name = "population") var population: Int,
    @ColumnInfo(name = "area") var area: Float,
    @ColumnInfo(name = "currency") var currency: String,
    @ColumnInfo(name = "language") var language: String,
    @ColumnInfo(name = "favourite") var favourite: Boolean
)