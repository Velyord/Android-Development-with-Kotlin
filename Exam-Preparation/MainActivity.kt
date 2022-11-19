package net.gostartups.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.gostartups.myapplication.adapter.CountryAdapter
import net.gostartups.myapplication.databinding.ActivityMainBinding
import net.gostartups.myapplication.db.AppDatabase
import net.gostartups.myapplication.factory.CountryViewModelFactory
import net.gostartups.myapplication.repository.CountryRepository
import net.gostartups.myapplication.service.CountryService
import net.gostartups.myapplication.util.NetworkUtil
import net.gostartups.myapplication.viewmodel.CountryViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var countryService: CountryService

    private lateinit var countryRepository: CountryRepository

    lateinit var countryViewModel: CountryViewModel

    lateinit var db: RoomDatabase

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://restcountries.com/v2/")
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
            countryViewModel.getCountries()
        }
    }

    private fun init() {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "countries_database"
        ).build()

        val countryDao = (db as AppDatabase).countryDao()
        countryService = retrofit.create(CountryService::class.java)
        countryRepository = CountryRepository(this, countryService, countryDao)
        val countryViewModelFactory = CountryViewModelFactory(countryRepository)
        countryViewModel =
            ViewModelProvider(this, countryViewModelFactory)[CountryViewModel::class.java]
    }

    private fun observeData() {
        GlobalScope.launch {
            countryViewModel.countriesList.collect {
                runOnUiThread {
                    val countries = it
                    val sortedCountries = countries.sortedByDescending { it.favourite }
                    val adapter = CountryAdapter(sortedCountries)
                    binding.countriesList.adapter = adapter
                    binding.tvCountriesCount.text = "Countries: ${it.size}"
                }
            }
        }
    }
}