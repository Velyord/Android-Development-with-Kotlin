package com.example.project8_3

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.project8_3.databinding.ActivitySecondBinding

class SecondActivity: FragmentActivity() {

    lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)

        val action = FirstFragmentDirections.actionGoToFirstFragment()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navHostFragment.navController.navigate(action)

        setContentView(binding.root)

        val title = intent.extras?.getString("title") ?: "Default Title"
        binding.tvTitle.text = title

        binding.btnPreviousActivity.setOnClickListener {
            finish()
        }
    }
}