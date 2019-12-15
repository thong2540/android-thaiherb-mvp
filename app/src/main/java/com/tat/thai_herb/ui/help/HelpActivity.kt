package com.tat.thai_herb.ui.help

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tat.thai_herb.R
import com.tat.thai_herb.extensions.darkenStatusBarColor
import kotlinx.android.synthetic.main.activity_help.*

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        darkenStatusBarColor(R.drawable.bg_toolbar)

        onEvent()
    }

    private fun onEvent() {
        clear_action_help.setOnClickListener {
            finish()
        }
    }
}
