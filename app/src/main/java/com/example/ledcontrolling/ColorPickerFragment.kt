package com.example.ledcontrolling


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.apandroid.colorwheel.ColorWheel
import com.apandroid.colorwheel.gradientseekbar.GradientSeekBar
import com.example.ledcontrolling.databinding.ColorPickerFragmentBinding

class ColorPickerFragment : Fragment(), AsyncResponse{

    private var color: Int = Color.BLACK
    private lateinit var binding: ColorPickerFragmentBinding

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.color_picker_fragment, container, false)

        createGradientSeekBars()
        listenForColorChanging()
        listenToSwitches()
        binding.sendButton.setOnClickListener {
            var connect: ConnectTask = ConnectTask()
            connect.delegate = this
            var hex: String = Integer.toHexString(color).substring(2, 8)
            hex += selectMode(hex)
            Toast.makeText(activity, hex, Toast.LENGTH_SHORT).show()
            //connect.execute(hex)
        }
        return binding.root
    }

    private fun listenToSwitches() {



        binding.apply {
            solidColorSwitch.setOnCheckedChangeListener{ _, isChecked ->
                if(isChecked){
                    flashingSwitch.isChecked = false
                    waveSwitch.isChecked = false
                }
                checkIfThereIsOneSwitchOn()
            }
            flashingSwitch.setOnCheckedChangeListener{ _, isChecked ->
                if(isChecked){
                    solidColorSwitch.isChecked = false
                    waveSwitch.isChecked = false
                }
                checkIfThereIsOneSwitchOn()
            }
            waveSwitch.setOnCheckedChangeListener{ _, isChecked ->
                if(isChecked){
                    flashingSwitch.isChecked = false
                    solidColorSwitch.isChecked = false
                }
                checkIfThereIsOneSwitchOn()
            }
            /*if(!(solidColorSwitch.isChecked || flashingSwitch.isChecked || waveSwitch.isChecked)){
                solidColorSwitch.isChecked = true
            }
*/

        }
    }

    private fun checkIfThereIsOneSwitchOn() {
        binding.apply {
            if (!(solidColorSwitch.isChecked || flashingSwitch.isChecked || waveSwitch.isChecked)) {
                solidColorSwitch.isChecked = true
            }
        }
    }

    private fun selectMode(hex: String): String {
        if(binding.solidColorSwitch.isChecked){
            return "s"
        }else if(binding.flashingSwitch.isChecked){
            return "f"
        }
        return "w"
    }


    private fun listenForColorChanging() {
        binding.colorWheel.colorChangeListener = { rgb: Int ->
            binding.apply {
                sendButton.setBackgroundColor(rgb)
                gradientSeekBarDark.endColor = rgb
                gradientSeekBarLight.startColor = rgb
            }
            color = rgb
        }

        binding.gradientSeekBarDark.listener = { _: Float, rgb: Int ->
            binding.sendButton.setBackgroundColor(rgb)
            color = rgb
        }
        binding.gradientSeekBarLight.listener = { _: Float, rgb: Int ->
            binding.sendButton.setBackgroundColor(rgb)
            color = rgb
        }
    }

    private fun createGradientSeekBars() {
        val startColor = Color.rgb(0, 0, 0)
        val endColor = Color.rgb(255, 255, 255)
        binding.apply {
            gradientSeekBarDark.startColor = startColor
            gradientSeekBarDark.endColor = endColor
            gradientSeekBarDark.offset = 0.5f
            gradientSeekBarDark.setColors(startColor, endColor)
            gradientSeekBarLight.startColor = startColor
            gradientSeekBarLight.endColor = endColor
            gradientSeekBarLight.offset = 0.5f
            gradientSeekBarLight.setColors(startColor, endColor)
        }
    }

    override fun processFinish(value: String?) {
        if(value == "error") {
            Toast.makeText(activity, "Cannot connect to the server", Toast.LENGTH_SHORT).show()
        }
    }
}