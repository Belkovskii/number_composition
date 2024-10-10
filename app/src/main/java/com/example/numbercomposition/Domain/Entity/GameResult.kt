package com.example.numbercomposition.Domain.Entity

data class GameResult(
    val isWin : Boolean,
    val countOfRightAnswer : Int,
    val countOfQuestions : Int,
    val gameSettings : GameSettings
) {
}