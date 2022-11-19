package com.example.crypto.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crypto.repository.CryptoRepository
import com.example.crypto.viewmodel.CryptoViewModel

class CryptoViewModelFactory(
    private val cryptoRepository: CryptoRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CryptoViewModel(cryptoRepository) as T
    }
}