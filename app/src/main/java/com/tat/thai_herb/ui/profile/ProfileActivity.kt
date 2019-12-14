package com.tat.thai_herb.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.tat.thai_herb.R
import com.tat.thai_herb.extensions.darkenStatusBar
import com.tat.thai_herb.listener.FragmentCallBack
import com.tat.thai_herb.ui.editProfile.DemoBottomSheetFragment
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private var images: String? = ""
    private var names: String? = ""
    private var email: String? = ""

    private lateinit var sheet: DemoBottomSheetFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        sheet = DemoBottomSheetFragment()
        this.darkenStatusBar()
        if (intent == null) return
        val  intent = intent
        images = intent.getStringExtra("image")
        names = intent.getStringExtra("name")
        email = intent.getStringExtra("email")

        setupView()

        onEvent()
    }

    private fun setupView() {

        Glide.with(this)
            .load(images)
            .into(image_profile)
        constraintLayout

        name_profile.text = names
        email_profile.text = email
    }

    private fun onEvent() {
        close_profile.setOnClickListener {
            finish()
        }

        linearLayout2.setOnClickListener {
            sheet.image = images
            sheet.name = names
            sheet.email = email
            sheet.show(supportFragmentManager, "DemoBottomSheetFragment")

        }

        sheet.bottomSheetListener(object : FragmentCallBack.CalBackEditProfile {
            override fun onSuccess(image: String, name: String) {
                images = image
                names = name
                setupView()
            }
        })
    }
}
