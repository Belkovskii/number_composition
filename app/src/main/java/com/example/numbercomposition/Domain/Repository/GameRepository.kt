package com.example.numbercomposition.Domain.Repository

import com.example.numbercomposition.Domain.Entity.GameSettings
import com.example.numbercomposition.Domain.Entity.Level
import com.example.numbercomposition.Domain.Entity.Question

interface GameRepository {
    fun generateQuestion(
        maxSumValue : Int,
        countOfOptions : Int
    ) : Question

    fun getGameSettings(level : Level) : GameSettings
}