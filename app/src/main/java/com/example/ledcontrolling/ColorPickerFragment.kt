package com.example.ledcontrolling


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.apandroid.colorwheel.ColorWheel
import com.apandroid.colorwheel.gradientseekbar.GradientSeekBar

class ColorPickerFragment : Fragment(), AsyncResponse{

    private var color: Int = Color.BLACK
    private lateinit var colorWheel: ColorWheel
    private lateinit var sendButton: Button
    private lateinit var gradientSeekBarDark: GradientSeekBar
    private lateinit var gradientSeekBarLight: GradientSeekBar


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        val view = inflater.inflate(R.layout.color_picker_fragment, container, false)
        colorWheel = view.findViewById<ColorWheel>(R.id.colorWheel)
        sendButton = view.findViewById<Button>(R.id.send_button)
        gradientSeekBarDark = view.findViewById<GradientSeekBar>(R.id.gradientSeekBar_dark)
        gradientSeekBarLight = view.findViewById<GradientSeekBar>(R.id.gradientSeekBar_light)

        createGradientSeekBars()
        listenForColorChanging()
        sendButton.setOnClickListener {
            var connect: ConnectTask = ConnectTask()
            connect.delegate = this
            var hex: String = Integer.toHexString(color).substring(2, 8)
            Toast.makeText(activity, hex, Toast.LENGTH_SHORT).show()
            //connect.execute(hex)
        }
        return view
    }

    private fun listenForColorChanging() {
        colorWheel.colorChangeListener = { rgb: Int ->
            sendButton.setBackgroundColor(rgb)
            gradientSeekBarDark.endColor = rgb
            gradientSeekBarLight.startColor = rgb
            color = rgb
        }

        gradientSeekBarDark.listener = { _: Float, rgb: Int ->
            sendButton.setBackgroundColor(rgb)
            color = rgb
        }
        gradientSeekBarLight.listener = { _: Float, rgb: Int ->
            sendButton.setBackgroundColor(rgb)
            color = rgb

        }
    }

    private fun createGradientSeekBars() {
        val startColor = Color.rgb(0, 0, 0)
        val endColor = Color.rgb(255, 255, 255)
        gradientSeekBarDark.startColor = startColor
        gradientSeekBarDark.endColor = endColor
        gradientSeekBarDark.offset = 0.5f
        gradientSeekBarDark.setColors(startColor, endColor)
        gradientSeekBarLight.startColor = startColor
        gradientSeekBarLight.endColor = endColor
        gradientSeekBarLight.offset = 0.5f
        gradientSeekBarLight.setColors(startColor, endColor)
    }

    override fun processFinish(value: String?) {
        if(value == "error") {
            Toast.makeText(activity, "Cannot connect to the server", Toast.LENGTH_SHORT).show()
        }
    }
}