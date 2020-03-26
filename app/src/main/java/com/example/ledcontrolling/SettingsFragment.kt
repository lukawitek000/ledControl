package com.example.ledcontrolling

import android.app.AlertDialog
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.ledcontrolling.databinding.SettingsFragmentBinding
import com.jakewharton.processphoenix.ProcessPhoenix
import kotlinx.android.synthetic.main.settings_fragment.view.*
import java.util.*

class SettingsFragment : Fragment(){


    private lateinit var binding: SettingsFragmentBinding
    private lateinit var alertDialog: AlertDialog.Builder

    companion object{
        const val POLISH  ="pl"
        const val ENGLISH  ="en"
         var language = ENGLISH

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.settings_fragment, container, false)

        createAlertDialog()
        binding.submitButton.setOnClickListener{
            alertDialog.show()
        }

       /* binding.selectPolishButton.setOnClickListener{
            if(language != POLISH) {
                language = POLISH
                setLanguage()
            }
        }

        binding.selectEnglishButton.setOnClickListener{
            if(language != ENGLISH) {
                language = ENGLISH
                setLanguage()
            }
        }*/



        return binding.root
    }

   /* @Suppress("DEPRECATION")
    private fun setLanguage() {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        val intent = Intent(activity, MainActivity::class.java)
        ProcessPhoenix.triggerRebirth(activity, intent)
        //binding.root.findNavController().navigate(R.id.action_settingsFragment_to_welcomeFragment)
    }*/

    private fun createAlertDialog() {
        alertDialog = AlertDialog.Builder(activity)
        alertDialog.apply {
            setTitle("Are you sure to change settings?")
            setMessage("It can disconnect phone with server")
            setPositiveButton("Yes"){_, _->
                ConnectTask.port = binding.inputPort.text.toString().toInt()
                ConnectTask.host = binding.inputIDaddress.text.toString()
                Toast.makeText(activity, "OK settings are changed", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("CANCEL"){_, _ ->
                binding.inputIDaddress.setText(ConnectTask.host)
                binding.inputPort.setText(ConnectTask.port.toString())
            }
        }
    }
}