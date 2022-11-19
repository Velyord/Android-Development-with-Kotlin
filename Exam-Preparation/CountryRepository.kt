package net.gostartups.myapplication.repository

import android.content.Context
import net.gostartups.myapplication.db.dao.CountryDao
import net.gostartups.myapplication.db.entity.CountryDetails
import net.gostartups.myapplication.model.CountryDetailResponse
import net.gostartups.myapplication.service.CountryService
import net.gostartups.myapplication.util.NetworkUtil

class CountryRepository constructor(
    private val context: Context,
    private val countryService: CountryService,
    private val countryDao: CountryDao
) {
    suspend fun getCountries(): List<CountryDetails> {
        return try {
            // if Internet connection is available fetch countries, save them to DB and return them
            if (NetworkUtil.isConnected(context)) {
                // execute the network request synchronously in order to wait for the response and handle it
                val countries = countryService.getCountries().execute().body()
                val roomCountries = countries?.map { mapResponseToDbModel(it) }
                countryDao.insertAll(roomCountries ?: return arrayListOf())

            }

            countryDao.getCountries()
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    suspend fun getCountryByName(name: String): CountryDetails? {
        return try {
            // if Internet connection is available fetch countries, save them to DB and return them
            if (NetworkUtil.isConnected(context)) {
                // execute the network request synchronously in order to wait for the response and handle it
                val countries = countryService.getCountryByName(name).execute().body()
                val roomCountries = countries?.map { mapResponseToDbModel(it) }
                countryDao.insertAll(roomCountries ?: return null)
            }

            return countryDao.getCountryByName(name)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun updateCountry(country: CountryDetails) {
        countryDao.update(country)
    }

    private fun mapResponseToDbModel(response: CountryDetailResponse): CountryDetails {
        return CountryDetails(
            name = response.name ?: "",
            capital = response.capital ?: "",
            flag = response.flags.png,
            alpha2Code = response.alpha2Code ?: "",
            callingCode = response.callingCodes[0],
            subregion = response.subregion ?: "",
            population = response.population ?: 0,
            area = response.area,
            currency = "${response.currencies?.getOrNull(0)?.code ?: ""} (${
                response.currencies?.getOrNull(
                    0
                )?.name ?: ""
            })",
            language = response.languages?.getOrNull(0)?.name ?: "",
            favourite = false
        )
    }
}