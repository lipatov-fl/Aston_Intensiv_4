package com.example.astonintensiv4.task_2.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val name: String,
    val surName: String,
    val image: String,
    val phone: String
) : Parcelable
