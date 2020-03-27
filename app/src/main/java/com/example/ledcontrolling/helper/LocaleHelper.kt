@file:Suppress("DEPRECATION")

package com.example.ledcontrolling.helper

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.preference.PreferenceManager
import java.util.*

class LocaleHelper {

    @Suppress("DEPRECATION")
    companion object{
        private const val SELECTED_LANGUAGE : String = "Locale.Helper.Selected.Language"



        fun onAttach(context: Context?, defaultLanguage: String?):Context? {
            val lang : String? = getPersistedData(context, defaultLanguage)
            return setLocale(context, lang)

        }

        fun setLocale(context: Context?, lang: String?): Context? {
            persist(context, lang)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                return updateResources(context, lang)
            return updateResourcesLegacy(context, lang)
        }


        @TargetApi(Build.VERSION_CODES.N)
        private fun updateResources(context: Context?, lang: String?): Context {
            val locale = Locale(lang!!)
            Locale.setDefault(locale)

            val config : Configuration = context!!.resources.configuration
            config.setLocale(locale)
            config.setLayoutDirection(locale)
            return context.createConfigurationContext(config)

        }


        @SuppressLint("ObsoleteSdkInt")
        @Suppress("DEPRECATION")
        private fun updateResourcesLegacy(context: Context?, lang: String?): Context? {
            val locale = Locale(lang!!)
            Locale.setDefault(locale)

            val resources : Resources = context!!.resources

            val config : Configuration = resources.configuration
            config.locale = locale

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
                config.setLayoutDirection(locale)
            }
            resources.updateConfiguration(config, resources.displayMetrics)
            return context

        }


        private fun persist(context: Context?, lang: String?) {
            val pref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor : SharedPreferences.Editor = pref.edit()
            editor.putString(SELECTED_LANGUAGE, lang)
            editor.apply()

        }

        private fun getPersistedData(context: Context?, language: String?): String? {
            val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getString(SELECTED_LANGUAGE, language)
        }


    }

}