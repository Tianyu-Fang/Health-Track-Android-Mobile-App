package com.example.assignment2.model

import com.google.gson.annotations.SerializedName

data class Checkin(
    //change
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("word")
    var word: String = ""

)