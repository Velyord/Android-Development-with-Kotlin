package net.gostartups.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.gostartups.myapplication.db.entity.CountryDetails
import net.gostartups.myapplication.repository.CountryRepository

class CountryViewModel(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val countriesListStateFlow = MutableStateFlow<List<CountryDetails>>(arrayListOf())

    private val selectedCountryStateFlow = MutableStateFlow<CountryDetails?>(null)

    val countriesList: StateFlow<List<CountryDetails>> = countriesListStateFlow.asStateFlow()

    val selectedCountry: StateFlow<CountryDetails?> = selectedCountryStateFlow.asStateFlow()

    suspend fun getCountries() {
        val countries = countryRepository.getCountries()
        countriesListStateFlow.value = countries
    }

    suspend fun getCountryByName(name: String) {
        val country = countryRepository.getCountryByName(name)
        selectedCountryStateFlow.value = country ?: return
    }

    suspend fun updateFavourites(country: CountryDetails) {
        countryRepository.updateCountry(country)
        selectedCountryStateFlow.value =
            selectedCountryStateFlow.value?.copy(favourite = country.favourite)
    }
}