package com.example.project7_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project7_2.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clickCount = "0"
        binding.myButton.setOnClickListener {

            val pics = arrayListOf<String>()
            pics.addAll(listOf("born2party", "chillin", "christian_dubstep", "dog", "horse", "lion", "lit", "owl", "violin", "whale"))
            val pic = pics[Random.nextInt(0, 10)]
            val picked = resources.getIdentifier(pic, "drawable",packageName)
            binding.myImageView.setImageResource(picked)

            //Показване на имената на снимките:
            //binding.myTextView.text = picked

            binding.clickCount = (Integer.parseInt(binding.clickCount)+1).toString()
        }
    }
}