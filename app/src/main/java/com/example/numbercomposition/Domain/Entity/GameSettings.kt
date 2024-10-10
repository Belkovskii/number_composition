package com.example.numbercomposition.Domain.Entity

data class GameSettings(
    val maxSumValue : Int,
    val minCountOfRightAnswer : Int,
    val minPercentOfRightAnswers : Int,
    val gameTimeInSeconds : Int
) {

}