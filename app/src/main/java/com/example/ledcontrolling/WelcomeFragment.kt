package com.example.ledcontrolling

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class WelcomeFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.welcome_fragment, container, false)
        val button = view.findViewById<Button>(R.id.button)

        button.setOnClickListener{
            view.findNavController().navigate(R.id.action_welcomeFragment_to_colorPickerFragment)
        }


        return view
    }
}