package com.example.assignment2.model

import android.os.Parcelable
import android.text.Editable
import kotlinx.parcelize.Parcelize

@Parcelize
class JournalModel(


    var activity: String = " ",


    var eat: String = " ",

    var feeling: String = " "

): Parcelable