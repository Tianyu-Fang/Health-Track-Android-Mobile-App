package com.example.assignment2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.view.MenuCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
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


}