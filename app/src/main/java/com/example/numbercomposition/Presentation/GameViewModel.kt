package com.example.numbercomposition.Presentation

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.numbercomposition.Data.GameRepositoryImpl
import com.example.numbercomposition.Domain.Entity.GameSettings
import com.example.numbercomposition.Domain.Entity.Level
import com.example.numbercomposition.Domain.Entity.Question
import com.example.numbercomposition.Domain.Usecases.GenerateQuestionUseCase
import com.example.numbercomposition.Domain.Usecases.GetGameSettingsUseCase

class GameViewModel : ViewModel() {

    // local vars - start
    private val repository = GameRepositoryImpl
    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)
    private lateinit var level : Level
    private lateinit var gameSettings : GameSettings
    private var timer : CountDownTimer? = null
    private var countOfQuestions = 0
    private var countOfRightAnswers = 0
    // local vars - end


    // live data scope - start

    /* - LD для отображения времени, оставшегося до конца игры */
    private val _formattedTimeLeftLD : MutableLiveData<String> = MutableLiveData()
    val formattedTimeLeftLD : LiveData<String>
        get() = _formattedTimeLeftLD

    private val _generatedQuestionLD : MutableLiveData<Question> = MutableLiveData()
    val generatedQuestionLD : LiveData<Question>
        get() = _generatedQuestionLD


    private val _percentOfRightAnswersLD : MutableLiveData<Int> = MutableLiveData()
    val percentOfRightAnswersLD : LiveData<Int>
        get() = _percentOfRightAnswersLD

    private val _progressAnswersLD : MutableLiveData<String> = MutableLiveData()
    val progressAnswersLD : LiveData<String>
        get() = _progressAnswersLD


    // live data scope - end




    // logic methods (call from fragment) - start
    fun startGame(level : Level) {
        getGameSettings(level)
        startTimer()
        generateQuestion()
    }

    fun checkAnswerAndGenerateNext(answer : Int) {
        checkAnswer(answer)
        generateQuestion()
    }
    // logic methods - end




    // service private methods - start
    private fun updateProgress() {

    }

    private fun calculateProgressPercent() : Int {
        return ((countOfRightAnswers/countOfQuestions.toDouble())*100).toInt()
    }
    private fun checkAnswer(answer : Int) {
        val rightAnswer = generatedQuestionLD.value?.rightAnswer
        val isRight = answer == rightAnswer
        countOfQuestions++
        if (isRight) {
            countOfRightAnswers++
        }
    }
    private fun generateQuestion() {
        _generatedQuestionLD.value = generateQuestionUseCase(gameSettings.maxSumValue)
    }
    private fun getGameSettings(level: Level) {
        this.level = level
        this.gameSettings = getGameSettingsUseCase(level)
    }
    private fun startTimer() {
        timer = object : CountDownTimer(
            gameSettings.gameTimeInSeconds * MILLS_IN_SECOND,
            MILLS_IN_SECOND) {
            override fun onTick(p0: Long) {
                val str = formatTime(p0)
                _formattedTimeLeftLD.value = str
            }

            override fun onFinish() {
                finishGame()
            }
        }
        timer?.start()
    }
    private fun finishGame() {

    }
    private fun formatTime(millisUntilFinish : Long) : String {
        val secondsUntilFinish = millisUntilFinish / MILLS_IN_SECOND
        val minutesUntilFinish = secondsUntilFinish / SECOND_IN_MINUTE
        val leftSeconds = secondsUntilFinish - (minutesUntilFinish * SECOND_IN_MINUTE)
        return String().format("%02d:%02d", minutesUntilFinish, leftSeconds)
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
    // service private methods - end


    // static
    companion object {
        const val MILLS_IN_SECOND = 1000L
        const val SECOND_IN_MINUTE = 60
    }
}