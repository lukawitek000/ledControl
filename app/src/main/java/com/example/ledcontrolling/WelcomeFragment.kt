package com.example.ledcontrolling

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.ledcontrolling.databinding.WelcomeFragmentBinding

class WelcomeFragment : Fragment(){

    private lateinit var binding: WelcomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.welcome_fragment, container, false)

        binding.goToLedButton.setOnClickListener{
            binding.root.findNavController().navigate(R.id.action_welcomeFragment_to_colorPickerFragment)

        }

        binding.goToSettingsButton.setOnClickListener{
            binding.root.findNavController().navigate(R.id.action_welcomeFragment_to_settingsFragment)
        }

        //binding.welcomeTextView.setShadowLayer(20f, 0f, 0f, Color.RED)
       // binding.goToSettingsButton.setShadowLayer(10f, 2f, -2f, Color.rgb(241, 102, 52))

       // binding.goToLedButton.setShadowLayer(10f, 2f, -2f, Color.rgb(241, 102, 52))


        return binding.root
    }
}