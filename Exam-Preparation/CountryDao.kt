package net.gostartups.myapplication.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import net.gostartups.myapplication.db.entity.CountryDetails

@Dao
interface CountryDao {

    @Query("SELECT * FROM countries")
    suspend fun getCountries(): List<CountryDetails>

    @Query("SELECT * FROM countries WHERE name=:name")
    suspend fun getCountryByName(name: String): CountryDetails

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(countries: List<CountryDetails>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(country: CountryDetails)
}