package com.example.assignment2

import android.app.Application

class CheckinApplication: Application() {
    val repository by lazy { CheckinRepository() }
}