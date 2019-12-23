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
    private var scoreFirst = 0
    private var scoreSecond = 0
    private var scoreThird = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        this.darkenStatusBar()
        if (intent == null) return
        val  intent = intent
        images = intent.getStringExtra("image")
        names = intent.getStringExtra("name")
        email = intent.getStringExtra("email")

        scoreFirst = intent.getIntExtra("scoreFirst",0)
        scoreSecond = intent.getIntExtra("scoreSecond",0)
        scoreThird = intent.getIntExtra("scoreThird",0)

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

        g_j.text = "$scoreFirst คะแนน"
        g_m.text = "$scoreSecond คะแนน"
        g_q.text = "$scoreThird คะแนน"
    }

    private fun onEvent() {
        close_profile.setOnClickListener {
            finish()
        }

    }
}
