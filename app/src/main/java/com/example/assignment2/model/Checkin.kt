package com.example.assignment2.model

import com.google.gson.annotations.SerializedName

data class Checkin(
    //change
    @SerializedName("symptom")
    var symptom: String = " ",

    @SerializedName("stress_level")
    var stress_level: Int = 0,

    @SerializedName("treatments")
    var treatments: String = " ",

    @SerializedName("health_factors")
    var health_factors: String = " "

)