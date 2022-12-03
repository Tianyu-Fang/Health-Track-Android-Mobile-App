package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class ProfileSubpage : AppCompatActivity()
{
        var appBarConfiguration: AppBarConfiguration? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_profilesubpage)
        supportFragmentManager.beginTransaction()
            .replace(
                android.R.id.content,  //android.R.id.content gives you the root element of a view, without having to know its actual name/type/ID.
                ProfileFragment()).commit()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController = findNavController(R.id.profile_subpage2)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeActivity2, R.id.checkinActivity2,R.id.workoutActivity2,R.id.chatActivity2, R.id.profileFragment2)
        )

        setupActionBarWithNavController(navController, appBarConfiguration!!)
        bottomNav.setupWithNavController(navController)


    }

//        override fun onNavigationItemSelected(item: MenuItem): Boolean {
//            val id = item.itemId
//            val navController = findNavController(R.id.profile_subpage2)
//            return when(id){
//                R.id.firstFragment ->{
//                    navController.navigate(R.id.homeActivity2)
//                    true
//                }
//                R.id.secondFragment ->{
//                    navController.navigate(R.id.checkinActivity2)
//                    true
//                }
//                R.id.thirdFragment ->{
//                    navController.navigate(R.id.workoutActivity2)
//                    true
//                }
//
//                R.id.fourthFragment ->{
//                    navController.navigate(R.id.chatActivity2)
//                    true
//                }
//               R.id.fifthFragment ->{
//                   navController.navigate(R.id.profileFragment2)
//                   true
//               }
//                else -> {
//                    false
//                }
//            }
//        }


    }