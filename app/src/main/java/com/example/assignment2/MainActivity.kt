package com.example.assignment2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.view.MenuCompat
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
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
            if (nd.id == R.id.profileFragment) {
                bottomNav.visibility = View.VISIBLE
            }
            else if(nd.id== R.id.settingFragment || nd.id == R.id.loginFragment){
                bottomNav.visibility = View.GONE
            }
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

        return when(id){
            R.id.measurementFragment ->  {
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
            R.id.logout ->{
//                val intent = Intent(this, LogoutActivity::class.java)
//                startActivity(intent)
                navController.navigate(R.id.logoutActivity_btm)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }

}