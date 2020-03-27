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
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.ledcontrolling.databinding.SettingsFragmentBinding
import io.paperdb.Paper
import kotlinx.android.synthetic.main.settings_fragment.view.*
import java.util.*

class SettingsFragment : Fragment(){
    private lateinit var binding: SettingsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.settings_fragment, container, false)

        binding.submitButton.setOnClickListener{
            createAlertDialog()

        }

       binding.selectPolishButton.setOnClickListener{
           if(Paper.book().read<String>("language") != "pl") {
               Paper.book().write("language", "pl")
               (activity as MainActivity).updateView(Paper.book().read<String>("language"))
               val toast = Toast.makeText(activity, resources.getString(R.string.language_changed), Toast.LENGTH_SHORT)
               val toastMessage : TextView = toast.view.findViewById(android.R.id.message)
               toastMessage.setTextColor(Color.RED)
               toast.view.setBackgroundColor(Color.BLACK)
               toast.view.background = resources.getDrawable(R.drawable.toast_drawable)
               toast.show()
           }
        }

        binding.selectEnglishButton.setOnClickListener{
            if(Paper.book().read<String>("language") != "en"){
                Paper.book().write("language", "en")
                (activity as MainActivity).updateView(Paper.book().read<String>("language"))
                Toast.makeText(activity, resources.getString(R.string.language_changed), Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }


    private fun createAlertDialog() {

        val dialog = Dialog(activity!!)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.alert_dialog_layout)

        val okButton = dialog.findViewById<Button>(R.id.okButton)
        val cancelButton = dialog.findViewById<Button>(R.id.cancelButton)

        okButton.setOnClickListener{
            ConnectTask.port = binding.inputPort.text.toString().toInt()
            ConnectTask.host = binding.inputIDaddress.text.toString()
            Toast.makeText(activity, resources.getString(R.string.setting_changes), Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        cancelButton.setOnClickListener{
            binding.inputIDaddress.setText(ConnectTask.host)
            binding.inputPort.setText(ConnectTask.port.toString())
            Toast.makeText(activity, resources.getString(R.string.cancel_text), Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.show()
    }


}