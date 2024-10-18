package com.android.fetch_rewards_2024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val navController: NavController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragMainAct) as NavHostFragment
    navHostFragment.navController
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_FetchRewards)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavMainAct)
        bottomNavigationView.setupWithNavController(navController)
    }
}