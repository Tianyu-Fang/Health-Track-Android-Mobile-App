package com.example.assignment2

import android.app.Application
import com.example.assignment2.repository.CheckinRepository

class CheckinApplication: Application() {
    val repository by lazy { CheckinRepository() }
}