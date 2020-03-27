package com.example.ledcontrolling

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
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
   // private lateinit var alertDialog: AlertDialog.Builder

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


        binding.submitButton.setOnClickListener{
            createAlertDialog()

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

        val dialog = Dialog(activity!!)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.alert_dialog_layout)

        val okButton = dialog.findViewById<Button>(R.id.okButton)
        val cancelButton = dialog.findViewById<Button>(R.id.cancelButton)

        okButton.setOnClickListener{
            ConnectTask.port = binding.inputPort.text.toString().toInt()
            ConnectTask.host = binding.inputIDaddress.text.toString()
            Toast.makeText(activity, "OK settings are changed", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        cancelButton.setOnClickListener{
            binding.inputIDaddress.setText(ConnectTask.host)
            binding.inputPort.setText(ConnectTask.port.toString())
            Toast.makeText(activity, "Cnacel", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.show()
    }


}