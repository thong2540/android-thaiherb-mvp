package com.tat.thai_herb.ui.description

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tat.thai_herb.R
import com.tat.thai_herb.utilty.StatusbarManager
import kotlinx.android.synthetic.main.activity_description.*
import kotlinx.android.synthetic.main.content_scrolling.*


class DescriptionActivity : AppCompatActivity() {

    private var isHideTolbarView: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        StatusbarManager.darkenStatusBar(this, R.drawable.bg_toolbar)
        if (intent == null) return


        showView()

        onEvent()
    }

    private fun showView() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        val  intent = intent
        val titleHerb = intent.getStringExtra("TitleHerb")
        val desHerb = intent.getStringExtra("DesHerb")

        toolbar_layout.title = titleHerb
        des_content_scrolling.text = desHerb
        Glide.with(this).load(intent.getStringExtra("ImgHerb")).into(imgViewHeaderDes)

    }

    private fun onEvent() {
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
