package com.example.crypto.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.crypto.db.entity.CryptoDetails
import com.example.crypto.repository.CryptoRepository

class CryptoViewModel(
    private val cryptoRepository: CryptoRepository
) : ViewModel() {

    private val cryptoesListStateFlow = MutableStateFlow<List<CryptoDetails>>(arrayListOf())
    private val selectedCryptoStateFlow = MutableStateFlow<CryptoDetails?>(null)
    val cryptoesList: StateFlow<List<CryptoDetails>> = cryptoesListStateFlow.asStateFlow()
    val selectedCrypto: StateFlow<CryptoDetails?> = selectedCryptoStateFlow.asStateFlow()

    suspend fun getCryptoes() {
        val cryptoes = cryptoRepository.getCryptoes()
        cryptoesListStateFlow.value = cryptoes
    }

    suspend fun getCryptoByName(name: String) {
        val crypto = cryptoRepository.getCryptoByName(name)
        selectedCryptoStateFlow.value = crypto ?: return
    }

    suspend fun updateFavourites(crypto: CryptoDetails) {
        cryptoRepository.updateCrypto(crypto)
        selectedCryptoStateFlow.value =
            selectedCryptoStateFlow.value?.copy(favourite = crypto.favourite)
    }
}