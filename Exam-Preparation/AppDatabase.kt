package net.gostartups.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import net.gostartups.myapplication.db.dao.CountryDao
import net.gostartups.myapplication.db.entity.CountryDetails

@Database(entities = [CountryDetails::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao
}