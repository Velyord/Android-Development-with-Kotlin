package com.example.crypto.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.crypto.db.dao.CryptoDao
import com.example.crypto.db.entity.CryptoDetails

@Database(entities = [CryptoDetails::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cryptoDao(): CryptoDao
}