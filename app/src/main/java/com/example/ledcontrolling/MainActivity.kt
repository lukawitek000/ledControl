package com.example.ledcontrolling

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.ledcontrolling.Helper.LocaleHelper
import com.google.android.material.navigation.NavigationView
import io.paperdb.Paper
import java.util.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    private lateinit var navController: NavController


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)



        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        navController = findNavController(R.id.nav_host_fragment)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        
        
        
        Paper.init(this)
        val language : String? = Paper.book().read("language")
        if(language == null){
            Paper.book().write("language", "en")
        }
        updateView(Paper.book().read<String>("language"))

        
    }

    fun updateView(lang: String?) {
        val context : Context? = LocaleHelper.setLocale(this, lang)
        val resources : Resources = context!!.resources
        navView.menu.apply {
            findItem(R.id.nav_settings).title = resources.getString(R.string.Settings_name)
            findItem(R.id.nav_home).title = resources.getString(R.string.home)
            findItem(R.id.nav_color_fill).title = resources.getString(R.string.ledButton)
        }
        navController.navigate(R.id.welcomeFragment)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

       when (item.itemId) {
            R.id.nav_home -> {
                Toast.makeText(this, "Home Screen", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.welcomeFragment)
            }
            R.id.nav_color_fill -> {
                Toast.makeText(this, "Color Picker", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.colorPickerFragment)
            }
           R.id.nav_settings -> {
               Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
               navController.navigate(R.id.settingsFragment)
           }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "en"))
    }
}
