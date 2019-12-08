package com.tat.thai_herb.ui.gallery

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.tat.thai_herb.R
import com.tat.thai_herb.extensions.hideStatusBar
import kotlinx.android.synthetic.main.activity_present_image.*

class PresentImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_present_image)
        this.hideStatusBar()
        if (intent == null) return

        onShowView()
        onEvent()
    }

    @SuppressLint("SetTextI18n")
    private fun onShowView() {
        val  intent = intent
        var name: String = intent.getStringExtra("name")
        Glide.with(this).load(intent.getStringExtra("part")).into(presentImage)
        textViewpresent.text = "รูป $name"
    }

    private fun onEvent() {
        close_image.setOnClickListener {
            finish()
        }
    }
}
