package com.example.ledcontrolling

import android.app.Application
import android.content.Context
import com.example.ledcontrolling.helper.LocaleHelper

class MainApplication : Application() {


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"))
    }
}