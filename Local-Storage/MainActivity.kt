package com.example.project9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.project9.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val countryInterface = retrofit.create(CountryInterface::class.java)
        val countryRepository = CountryRepository(countryInterface)

        countryRepository.getCountries()?.enqueue(object : Callback<List<CountryData>> {
            override fun onResponse(
                call: Call<List<CountryData>>,
                response: Response<List<CountryData>>
            ) {
                val countries = response.body() ?: return
                val adapter = CountryAdapter(countries)
                binding.countriesList.adapter = adapter
            }

            override fun onFailure(call: Call<List<CountryData>>, t: Throwable) {
                // log error message
                // show error to user
            }
        })
    }
}