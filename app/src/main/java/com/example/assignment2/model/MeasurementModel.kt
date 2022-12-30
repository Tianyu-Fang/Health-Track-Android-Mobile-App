package com.example.assignment2.model

import android.os.Parcelable
import android.text.Editable
import kotlinx.parcelize.Parcelize

@Parcelize
class MeasurementModel(
    var height: Int = 0,

    var weight: Int = 0,

    var glucose: Int = 0,

    var pressure: String = " ",


    var breathing: Int = 0,

    var oxygen: Int = 0,

    var temperature: Int = 0,

    var pulse: Int = 0
): Parcelable