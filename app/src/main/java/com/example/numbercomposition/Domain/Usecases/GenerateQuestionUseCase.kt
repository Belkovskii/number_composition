package com.example.numbercomposition.Domain.Usecases

import com.example.numbercomposition.Domain.Entity.Level
import com.example.numbercomposition.Domain.Entity.Question
import com.example.numbercomposition.Domain.Repository.GameRepository

class GenerateQuestionUseCase(private val repository : GameRepository) {

    operator fun invoke(maxSumValue : Int) : Question {
        return repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    companion object {
        private const val COUNT_OF_OPTIONS = 6
    }

}