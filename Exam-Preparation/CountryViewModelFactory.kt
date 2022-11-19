package net.gostartups.myapplication.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.gostartups.myapplication.repository.CountryRepository
import net.gostartups.myapplication.viewmodel.CountryViewModel

class CountryViewModelFactory(
    private val countryRepository: CountryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountryViewModel(countryRepository) as T
    }
}