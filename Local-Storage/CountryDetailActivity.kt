package com.example.project9

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import androidx.room.Room
import com.example.project9.databinding.ActivityCountryDetailBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class CountryDetailActivity: FragmentActivity() {
    lateinit var binding: ActivityCountryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCountryDetailBinding.inflate(layoutInflater)

        val action = CountryFragmentDirections.actionGoToCountryFragment()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navHostFragment.navController.navigate(action)

        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            CountryDatabase::class.java,
            "my_database_name"
        ).build()

        val userDao = db.userDao()

        GlobalScope.async {
            val users = userDao?.getAll()
            val userFirstName = users?.get(0)?.firstName ?: "No first name"
            binding.usersCount.text = "${users?.count()}\n$userFirstName"

//            val user = User(
//                1,
//                "Yoan",
//                "Yordanov"
//            )
//            userDao?.insert(user)
        }

    // CountryDetailActivity | gets country name -> passes it to CountryFragment
        val name = intent.extras?.getString("name") ?: "Bulgaria"

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val countryFragment = CountryFragment()

        val bundle = Bundle()
        bundle.putString("name", name)
        countryFragment.arguments = bundle
        fragmentTransaction.add(R.id.container, countryFragment).commit()

        binding.btnPreviousActivity.setOnClickListener {
            finish()
        }
    }
}