package com.example.numbercomposition.Presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.numbercomposition.Domain.Entity.Level
import com.example.numbercomposition.R
import com.example.numbercomposition.databinding.FragmentChooseLevelBinding

class ChooseLevelFragment : Fragment() {

    private var _binding : FragmentChooseLevelBinding? = null
    private val binding : FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("Fragment choose level binding is null")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLevelEasy.setOnClickListener{
            openGameFragment(Level.EASY)
        }
        binding.buttonLevelHard.setOnClickListener{
            openGameFragment(Level.HARD)
        }
        binding.buttonLevelNormal.setOnClickListener{
            openGameFragment(Level.NORMAL)
        }
        binding.buttonLevelTest.setOnClickListener{
            openGameFragment(Level.TEST)
        }
    }

    private fun openGameFragment(level : Level) {
        val activity = requireActivity()
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.createInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val NAME = "ChooseLevelFragment"
        fun createInstance() : ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }
}