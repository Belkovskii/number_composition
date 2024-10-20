package com.example.numbercomposition.Data

import com.example.numbercomposition.Domain.Entity.GameSettings
import com.example.numbercomposition.Domain.Entity.Level
import com.example.numbercomposition.Domain.Entity.Question
import com.example.numbercomposition.Domain.Repository.GameRepository
import java.lang.Integer.max
import kotlin.math.min
import kotlin.random.Random


object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_VALUE = 2;
    private const val MIN_ANSWER_VALUE = 1;
    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val number = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, number)
        val rightAnswer = number - visibleNumber
        val tempVariants = HashSet<Int>()
        tempVariants.add(rightAnswer)
        val from = max(rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfOptions)
        while (tempVariants.size < countOfOptions) {
            tempVariants.add(Random.nextInt(from, to))
        }
        return Question(number, visibleNumber, tempVariants.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> GameSettings(
                10,
                3,
                50,
                8
            )
            Level.EASY -> GameSettings(
                10,
                10,
                70,
                60
            )
            Level.NORMAL -> GameSettings(
                20,
                20,
                80,
                40
            )
            Level.HARD -> GameSettings(
                30,
                30,
                90,
                40
            )
        }
    }
}