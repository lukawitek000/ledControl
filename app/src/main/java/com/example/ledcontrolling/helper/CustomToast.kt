package com.example.ledcontrolling.helper

import android.app.Activity
import android.graphics.Color
import android.widget.TextView
import android.widget.Toast
import com.example.ledcontrolling.R

class CustomToast {

    companion object{
        fun show(activity : Activity, text : String, duration: Int){
            val toast = Toast.makeText(activity, text, duration)
            val toastMessage : TextView = toast.view.findViewById(android.R.id.message)
            toastMessage.setTextColor(Color.RED)
            toast.view.setBackgroundColor(Color.BLACK)
            @Suppress("DEPRECATION")
            toast.view.background = activity.resources.getDrawable(R.drawable.toast_drawable)
            toast.show()
        }
    }

}