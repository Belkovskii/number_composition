package com.example.numbercomposition.Presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.numbercomposition.R
import com.example.numbercomposition.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding : FragmentWelcomeBinding
        get() = _binding ?: throw RuntimeException("binding is null")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonUnderstand.setOnClickListener{
            switchToChooseLevelFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun switchToChooseLevelFragment() {
        val activity = requireActivity()
        val fragmentManager = activity.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, ChooseLevelFragment.createInstance())
            .addToBackStack(ChooseLevelFragment.NAME)
            .commit()
    }

}