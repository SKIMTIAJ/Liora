package com.news.liora.activity.homeactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.news.liora.R
import com.news.liora.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private var _binding:ActivityHomeBinding?=null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerHome) as NavHostFragment

        navController = navHostFragment.navController
        binding.bottomNegigation.setupWithNavController(navController)
    }

}