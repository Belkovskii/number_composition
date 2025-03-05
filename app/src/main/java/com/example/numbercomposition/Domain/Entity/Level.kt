package com.example.numbercomposition.Domain.Entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Level() : Parcelable {
    TEST, EASY, NORMAL, HARD
}
