package com.example.ledcontrolling

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.*

class LocaleManager {

    companion object {

        fun setLocale(c: Context) {
            setNewLocale(c, getLanguage(c));
        }

        private fun setNewLocale(c: Context, language: String)
        {
            persistLanguage(c, language);
            updateResources(c, language);
        }

        private fun getLanguage(c: Context): String
        {
            return SettingsFragment.language
        }

        private fun persistLanguage(c: Context, language:String)
        {

        }

        private fun updateResources(context: Context, language: String)
        {
            var locale = Locale(language);
            Locale.setDefault(locale);

            val res = context.resources;
            var config = Configuration(res.configuration);
            if (Build.VERSION.SDK_INT >= 17) {
                config.setLocale(locale);
            } else {
                config.locale = locale;
            }
            res.updateConfiguration(config, res.displayMetrics);
        }
    }
}