package com.example.numbercomposition.Presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.numbercomposition.Domain.Entity.GameResult
import com.example.numbercomposition.Domain.Entity.GameSettings
import com.example.numbercomposition.Domain.Entity.Level
import com.example.numbercomposition.R
import com.example.numbercomposition.databinding.FragmentGameBinding
import kotlin.concurrent.thread

class GameFragment : Fragment() {

//    private val gameViewModel : GameViewModel

    private lateinit var level: Level

    private var _binding : FragmentGameBinding? = null
    private val binding : FragmentGameBinding
        get() = _binding ?: throw RuntimeException("game fragment binding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deserializeLevel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSum.setOnClickListener{
            val gs = GameSettings(1, 1, 1, 1)
            val gr = GameResult(true, 5, 5, gs)
            launchGameFinishedFragment(gr)
        }
        lifecycleScope.launchWhenStarted {

        }
    }

    private fun launchGameFinishedFragment(gr : GameResult) {
        val activity = requireActivity()
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.createInstance(gr))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun deserializeLevel() {
        val args = requireArguments()
        args.getParcelable<Level>(KEY_LEVEL)?.let { level = it}
    }

    companion object {

        const val NAME = "GameFragment"

        private const val KEY_LEVEL = "level"

        fun createInstance(level : Level) : GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }

    }

}