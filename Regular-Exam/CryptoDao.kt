package com.example.crypto.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.crypto.db.entity.CryptoDetails

@Dao
interface CryptoDao {

    @Query("SELECT * FROM cryptoes")
    suspend fun getCryptoes(): List<CryptoDetails>

    @Query("SELECT * FROM cryptoes WHERE name=:name")
    suspend fun getCryptoByName(name: String): CryptoDetails

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(cryptoes: List<CryptoDetails>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(crypto: CryptoDetails)
}