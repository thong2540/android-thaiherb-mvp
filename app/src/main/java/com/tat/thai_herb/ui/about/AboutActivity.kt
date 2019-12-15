package com.tat.thai_herb.ui.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tat.thai_herb.R
import com.tat.thai_herb.extensions.darkenStatusBarColor
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        darkenStatusBarColor(R.drawable.bg_toolbar)

        onEvent()

    }

    private fun onEvent() {
        clear_action_about.setOnClickListener {
            finish()
        }
    }
}
