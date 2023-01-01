package com.example.assignment2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Workout(
    var goal: Int = 0,

    var walking_time: Int = 0,

    var running_time: Int = 0,

    var other_time: Int = 0
) : Parcelable