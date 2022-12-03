package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    var appBarConfiguration: AppBarConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_t)
        val navController = findNavController(R.id.bottomNavigation)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.dashboardFragment_btm, R.id.checkinBoardFragment_btm,R.id.workoutActivity_btm,R.id.chatActivity_btm,R.id.profileFragment_btm)
        )

        setupActionBarWithNavController(navController, appBarConfiguration!!)
        bottomNav.setupWithNavController(navController)

    }

}