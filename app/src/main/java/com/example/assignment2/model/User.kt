package com.example.assignment2.model

import android.os.Parcelable
import android.text.Editable
import kotlinx.parcelize.Parcelize


@Parcelize
class User(
    var userEmail: String? = null,
    var userName: String? = null,
    var bloodType: String? = null,
    var gender: String? = null,
    var DoB: String? = null,
    var height: String? = null,
    var weight: String? = null
) : Parcelable

