package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ProfileSubpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_profilesubpage)
        supportFragmentManager.beginTransaction()
            .replace(
                android.R.id.content,  //android.R.id.content gives you the root element of a view, without having to know its actual name/type/ID.
                CheckinFragment()).commit()

    }


}