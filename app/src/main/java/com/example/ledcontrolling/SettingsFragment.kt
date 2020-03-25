package com.example.ledcontrolling

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.ledcontrolling.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment(){


    private lateinit var binding: SettingsFragmentBinding
    private lateinit var alertDialog: AlertDialog.Builder


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
        return binding.root
    }

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