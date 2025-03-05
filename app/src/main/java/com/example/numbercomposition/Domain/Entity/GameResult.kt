package com.example.numbercomposition.Domain.Entity

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameResult(
    val isWin: Boolean,
    val countOfRightAnswer: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings?
) : Parcelable {}