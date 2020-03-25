package com.example.ledcontrolling

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var fragment: Fragment
    private lateinit var fragmentManager: FragmentManager

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
       /* navView = findViewById(R.id.nav_view)
        fragment = WelcomeFragment()
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.placeholder, fragment).commit()

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)*/
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

       /* when (item.itemId) {
            R.id.nav_home -> {
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
                fragment = WelcomeFragment()
            }
            R.id.nav_color_fill -> {
                Toast.makeText(this, "Messages clicked", Toast.LENGTH_SHORT).show()
                fragment = ColorPickerFragment()
            }
            R.id.nav_update -> {
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        fragmentManager.beginTransaction().replace(R.id.placeholder, fragment).commit()
*/

        return true
    }
}
