package com.example.project8_3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.example.project8_3.databinding.FragmentForthBinding

class ForthFragment: Fragment() {
    lateinit var binding: FragmentForthBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForthBinding.inflate(inflater, container, false)

        binding.nextFragment.setOnClickListener {
            val action = ForthFragmentDirections.actionGoToFirstFragment()
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.container) as NavHostFragment
            navHostFragment.navController.navigate(action)

            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            // сложил съм във всички <fragment-и> action_go_to_second_fragment, защото ми дава
            // illegalArgumentException, Navigation action action_go_to_second_fragment cannot be
            // found from the current destination (давайки ми различен от firstFragment фрагмент,
            // когато натискам back бутона повече от веднъж при тестовете
            // този ред не дава тази грешка, когато използвам transaction-и
        }
        return binding.root
    }
}