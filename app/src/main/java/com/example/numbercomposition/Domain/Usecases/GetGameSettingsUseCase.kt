package com.example.numbercomposition.Domain.Usecases

import com.example.numbercomposition.Domain.Entity.GameSettings
import com.example.numbercomposition.Domain.Entity.Level
import com.example.numbercomposition.Domain.Repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {
    operator fun invoke(level : Level) : GameSettings {
        return repository.getGameSettings(level)
    }

}