package com.example.project8_3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.project8_3.databinding.FragmentSecondBinding

class SecondFragment: Fragment() {
    lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)

        binding.nextFragment.setOnClickListener {
            val action = SecondFragmentDirections.actionGoToThirdFragment()
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.container) as NavHostFragment
            navHostFragment.navController.navigate(action)
        }

        return binding.root
    }
}