package com.tat.thai_herb.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.tat.thai_herb.R
import com.tat.thai_herb.extensions.darkenStatusBar
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        this.darkenStatusBar()
        if (intent == null) return

        setupView()

        onEvent()
    }

    private fun setupView() {
        val  intent = intent
        val image = intent.getStringExtra("image")
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")

        Glide.with(this)
            .load(image)
            .into(image_profile)
        constraintLayout

        name_profile.text = name
        email_profile.text = email
    }

    private fun onEvent() {
        close_profile.setOnClickListener {
            finish()
        }
    }
}
