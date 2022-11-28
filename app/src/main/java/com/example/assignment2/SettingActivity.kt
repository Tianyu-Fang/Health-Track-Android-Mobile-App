package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.fragment_setting)

        supportFragmentManager.beginTransaction()
            .replace(
                android.R.id.content,  //android.R.id.content gives you the root element of a view, without having to know its actual name/type/ID.
                SettingFragment()).commit()

    }

}