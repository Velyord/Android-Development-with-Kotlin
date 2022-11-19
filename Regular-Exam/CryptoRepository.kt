package com.example.crypto.repository

import android.content.Context
import com.example.crypto.db.dao.CryptoDao
import com.example.crypto.db.entity.CryptoDetails
import com.example.crypto.model.CryptoDetailResponse
import com.example.crypto.service.CryptoService
import com.example.crypto.util.NetworkUtil

class CryptoRepository constructor(
    private val context: Context,
    private val cryptoService: CryptoService,
    private val cryptoDao: CryptoDao
) {
    suspend fun getCryptoes(): List<CryptoDetails> {
        return try {
            // if Internet connection is available fetch cryptoes, save them to DB and return them
            if (NetworkUtil.isConnected(context)) {
                // execute the network request synchronously in order to wait for the response and handle it
                val cryptoes = cryptoService.getCryptoes().execute().body()
                val roomCryptoes = cryptoes?.map { mapResponseToDbModel(it) }
                cryptoDao.insertAll(roomCryptoes ?: return arrayListOf())

            }

            cryptoDao.getCryptoes()
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    suspend fun getCryptoByName(name: String): CryptoDetails? {
        return try {
            // if Internet connection is available fetch cryptoes, save them to DB and return them
            if (NetworkUtil.isConnected(context)) {
                // execute the network request synchronously in order to wait for the response and handle it
                val cryptoes = cryptoService.getCryptoByName().execute().body()
                val roomCryptoes = cryptoes?.map { mapResponseToDbModel(it) }
                cryptoDao.insertAll(roomCryptoes ?: return null)
            }

            return cryptoDao.getCryptoByName(name)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun updateCrypto(crypto: CryptoDetails) {
        cryptoDao.update(crypto)
    }

    private fun mapResponseToDbModel(response: CryptoDetailResponse): CryptoDetails {
        return CryptoDetails(
            name = response.name ?: "",
            symbol = response.symbol ?: "",
            image = response.image ?: "",
            price = response.price ?: 0.0,
            marketCap = response.marketCap ?: 0,
            high24h = response.high24h ?: 0.0,
            priceChangePercentage24h = response.priceChangePercentage24h ?: 0.0,
            marketCapChangePercentage24h = response.marketCapChangePercentage24h ?: 0.0,
            low24h = response.low24h ?: 0.0,
            favourite = false
        )
    }
}