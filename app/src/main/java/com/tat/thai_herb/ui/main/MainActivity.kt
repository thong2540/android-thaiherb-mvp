package com.tat.thai_herb.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.tat.thai_herb.R
import com.tat.thai_herb.ui.game.GameFragment
import com.tat.thai_herb.ui.home.HomeFragment
import com.tat.thai_herb.ui.login.LoginActivity
import com.tat.thai_herb.ui.profile.ProfileFragment
import com.tat.thai_herb.utilty.ActivityTransition
import com.tat.thai_herb.utilty.StatusbarManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StatusbarManager.darkenStatusBar(this, R.drawable.bg_toolbar)
        firebaseAuth = FirebaseAuth.getInstance()

        onEvent()

        navigation.selectedItemId = R.id.home
    }

    private fun onEvent() {

        logoutMain.setOnClickListener {
            firebaseAuth!!.signOut()

            firebaseAuth!!.addAuthStateListener {
                if(firebaseAuth!!.currentUser == null){
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    ActivityTransition.GoLeft(this)
                    this.finish()
                }
            }
        }

        navigation.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                var fragment: Fragment? = null
                when (item.itemId) {
                    R.id.home -> {
                        textViewMain.text = "สมุนไพรไทย"
                        fragment = HomeFragment()
                    }
                    R.id.game -> {
                        textViewMain.text = "เกมสมุนไพร"
                        fragment = GameFragment()
                    }
                    R.id.gallery -> {
                        textViewMain.text = "แกลอรี่สมุนไพร"
                        fragment = ProfileFragment()
                    }
                    R.id.profile -> {
                        textViewMain.text = "โปรไฟล์"
                        fragment = ProfileFragment()
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

