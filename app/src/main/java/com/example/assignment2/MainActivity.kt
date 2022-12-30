package com.example.assignment2

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.view.MenuCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.assignment2.repository.AuthRepository
import com.example.assignment2.viewmodel.AuthViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    var appBarConfiguration: AppBarConfiguration? = null
    private val viewModel: AuthViewModel by viewModels {
        AuthViewModel.Provider(AuthRepository.repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_t)
        val navController = findNavController(R.id.bottomNavigation)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.dashboardFragment_btm,
                R.id.checkinBoardFragment_btm,
                R.id.workoutFragment_btm,
                R.id.chatFragment_btm,
                R.id.profileFragment
            )
        )
//
//        supportActionBar?.setDisplayShowHomeEnabled(false)
//        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        setupActionBarWithNavController(navController, appBarConfiguration!!)
        bottomNav.setupWithNavController(navController)
        bottomNav.visibility = View.GONE
        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id == R.id.profileFragment || nd.id == R.id.dashboardFragment_btm) {
                bottomNav.visibility = View.VISIBLE
            } else if (nd.id == R.id.settingFragment || nd.id == R.id.loginFragment || nd.id == R.id.profileUpdateFragment) {
                bottomNav.visibility = View.GONE
            }
        }

        val sharedPreference = getSharedPreferences("user", Context.MODE_PRIVATE)

        val user_id : String? = sharedPreference.getString("user_id",null)
            if(user_id==null) {
                navController.navigate(R.id.loginFragment)
            }
            else{
                viewModel.setUserEmail(user_id)
                navController.navigate(R.id.dashboardFragment_btm)
            }

    }

        @SuppressLint("RestrictedApi")
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_options, menu)

            // Show icons on Overflow option menu
            if (menu is MenuBuilder) {
                menu.setOptionalIconsVisible(true)
            }

            MenuCompat.setGroupDividerEnabled(menu!!, true)

            return super.onCreateOptionsMenu(menu)
        }

        override fun onSupportNavigateUp(): Boolean {
            val navController = this.findNavController(R.id.bottomNavigation)
            return (NavigationUI.navigateUp(navController, appBarConfiguration!!)
                    || super.onSupportNavigateUp())
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val id = item.itemId
            val navController = findNavController(R.id.bottomNavigation)

            return when (id) {
                R.id.measurementFragment -> {
                    navController.navigate(R.id.measurementFragment_btm)
                    true
                }
                R.id.infoFragment -> {
                    navController.navigate(R.id.viewInfoFragment_info_btm)
                    true
                }
                R.id.dietFragment -> {
                    navController.navigate(R.id.dietFragment_btm)
                    true
                }
                R.id.journalFragment -> {
                    navController.navigate(R.id.journalFragment_btm)
                    true
                }
                R.id.sleepFragment -> {
                    navController.navigate(R.id.sleepFragment_btm)
                    true
                }
                R.id.logout -> {
//                val intent = Intent(this, LogoutActivity::class.java)
//                startActivity(intent)
                    //after log out, remove the user_id data
                    val sharedPreference =
                        getSharedPreferences("user", Context.MODE_PRIVATE)
                    val editor = sharedPreference?.edit()
                    if (editor != null) {
                        editor.putString("user_id", null)
                        editor.commit()
                    }
                        navController.navigate(R.id.loginFragment)
                    true
                }

                else -> {
                    super.onOptionsItemSelected(item)
                }
            }

        }

}