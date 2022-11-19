package com.example.project9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project9.databinding.FragmentCountryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryFragment: Fragment() {
    lateinit var binding: FragmentCountryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryBinding.inflate(inflater, container, false)

    // CountryFragment | "gets" country name -> passes it in the api link
        val data = arguments
        val name = data?.get("name")

        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/v2/name/${name}/")
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
        return binding.root
    }
}