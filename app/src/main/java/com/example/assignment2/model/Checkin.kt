package com.example.assignment2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Checkin(
    //change
    var symptom: String = " ",

    var stress_level: Int = 0,

    var treatments: String = " ",

    var health_factors: String = " "

) : Parcelable