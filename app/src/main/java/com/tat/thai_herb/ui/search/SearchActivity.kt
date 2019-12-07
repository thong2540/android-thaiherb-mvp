package com.tat.thai_herb.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.tat.thai_herb.R
import com.tat.thai_herb.extensions.hideKeyboard
import com.tat.thai_herb.utilty.StatusbarManager
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.view.*
import kotlinx.android.synthetic.main.header_toolbar_search.view.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        StatusbarManager.darkenStatusBar(this, R.drawable.bg_toolbar)

        onEvent()
    }

    private fun onEvent() {
        heard_search.imageViewDelete_search.setOnClickListener {
            heard_search.editTextSearch.setText("")
        }

        heard_search.textViewDisSearch.setOnClickListener {
            this.hideKeyboard()
            finish()
        }

    }
}
