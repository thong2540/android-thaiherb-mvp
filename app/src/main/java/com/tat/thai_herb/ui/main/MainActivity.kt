package com.tat.thai_herb.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tat.thai_herb.R
import com.tat.thai_herb.extensions.darkenStatusBarColor
import com.tat.thai_herb.ui.gallery.GalleryFragment
import com.tat.thai_herb.ui.game.GameFragment
import com.tat.thai_herb.ui.home.HomeFragment
import com.tat.thai_herb.ui.pagemenu.MenuFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header_toolbar_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        darkenStatusBarColor(R.drawable.bg_toolbar)
        onEvent()

        navigation.selectedItemId = R.id.home
    }

    private fun onEvent() {

        navigation.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                var fragment: Fragment? = null
                when (item.itemId) {
                    R.id.home -> {
                        fragment = HomeFragment()
                        heard_main.textViewMain.text = "สมุนไพรไทย"
                    }
                    R.id.gallery -> {
                        fragment = GalleryFragment()
                        heard_main.textViewMain.text = "แกลลอรี่"
                    }
                    R.id.game -> {
                        fragment = GameFragment()
                        heard_main.textViewMain.text = "เกม"
                    }
                    R.id.menu -> {
                        fragment = MenuFragment()
                        heard_main.textViewMain.text = "เมนู"
                    }
                }
                return loadFragment(fragment)
            }

            private fun loadFragment(fragment: Fragment?): Boolean {
                if (fragment != null) {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame, fragment)
                        .commit()
                    return true
                }
                return false
            }
        })
    }
}

