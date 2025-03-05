package com.example.numbercomposition.Domain.Entity

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings (
    val maxSumValue : Int,
    val minCountOfRightAnswer : Int,
    val minPercentOfRightAnswers : Int,
    val gameTimeInSeconds : Int
) : Parcelable {}