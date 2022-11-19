package com.example.project9

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class CountryDatabase: RoomDatabase(){
    abstract fun userDao(): UserDao
}
