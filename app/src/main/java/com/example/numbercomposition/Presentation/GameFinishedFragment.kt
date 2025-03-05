package com.example.numbercomposition.Presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.numbercomposition.Domain.Entity.GameResult
import com.example.numbercomposition.R
import com.example.numbercomposition.databinding.FragmentGameFinishedBinding

class GameFinishedFragment : Fragment() {

    private lateinit var gameResult : GameResult

    private var _binding : FragmentGameFinishedBinding? = null
    private val binding : FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("game finished binding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val result = requireArguments().getParcelable<GameResult>(KEY_RESULT)
        gameResult = result ?: throw RuntimeException("wrong game result")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                backToChooseLevel()
            }
        })
        binding.tryAgainButton.setOnClickListener{
            backToChooseLevel()
        }
    }

    private fun backToChooseLevel() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStack(
            GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(ChooseLevelFragment.NAME, 0)
    }

    companion object {

        private const val KEY_RESULT = "result"

        fun createInstance(gameResult : GameResult) : GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_RESULT, gameResult)
                }
            }
        }

    }
}