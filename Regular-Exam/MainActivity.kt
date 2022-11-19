package com.example.crypto.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.crypto.adapter.CryptoAdapter
import com.example.crypto.databinding.ActivityMainBinding
import com.example.crypto.db.AppDatabase
import com.example.crypto.factory.CryptoViewModelFactory
import com.example.crypto.repository.CryptoRepository
import com.example.crypto.service.CryptoService
import com.example.crypto.util.NetworkUtil
import com.example.crypto.viewmodel.CryptoViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cryptoService: CryptoService
    private lateinit var cryptoRepository: CryptoRepository
    lateinit var cryptoViewModel: CryptoViewModel
    lateinit var db: RoomDatabase

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.coingecko.com/api/v3/coins/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        init()
        observeData()

        if (!NetworkUtil.isConnected(this)) {
            Snackbar.make(
                binding.root,
                "No internet connection, information could be outdated",
                Snackbar.LENGTH_LONG
            ).show()
        }

        GlobalScope.launch {
            cryptoViewModel.getCryptoes()
        }
    }

    private fun init() {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "cryptoes_database"
        ).build()

        val cryptoDao = (db as AppDatabase).cryptoDao()
        cryptoService = retrofit.create(CryptoService::class.java)
        cryptoRepository = CryptoRepository(this, cryptoService, cryptoDao)
        val cryptoViewModelFactory = CryptoViewModelFactory(cryptoRepository)
        cryptoViewModel = ViewModelProvider(this, cryptoViewModelFactory)[CryptoViewModel::class.java]
    }

    private fun observeData() {
        GlobalScope.launch {
            cryptoViewModel.cryptoesList.collect {
                runOnUiThread {
                    val cryptoes = it
                    val sortedCryptoes = cryptoes.sortedByDescending { it.favourite }
                    val adapter = CryptoAdapter(sortedCryptoes)
                    binding.cryptoesList.adapter = adapter
                    binding.tvCryptoesCount.text = "Cryptoes: ${it.size}"
                }
            }
        }
    }
}