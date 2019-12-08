package com.tat.thai_herb.ui.description

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tat.thai_herb.R
import com.tat.thai_herb.utilty.StatusbarManager
import kotlinx.android.synthetic.main.activity_description.*
import kotlinx.android.synthetic.main.activity_description.view.*
import kotlinx.android.synthetic.main.content_scrolling.*


class DescriptionActivity : AppCompatActivity() {

    private var handler: Handler? = null
    private var runnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        StatusbarManager.darkenStatusBar(this, R.drawable.bg_toolbar)
        handler = Handler()
        if (intent == null) return


        showView()

        onEvent()

        runnable = Runnable {
            onVisibleView()
        }
    }



    private fun showView() {
        onGoneView()
        setSupportActionBar(toolbar)
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

    private fun onVisibleView() {
        loadingDes.clearAnimation()
        loadingDes.visibility = View.GONE

        app_bar.visibility = View.VISIBLE
        include_des.visibility = View.VISIBLE
    }

    private fun onGoneView() {
        app_bar.visibility = View.GONE
        include_des.visibility = View.GONE

        loadingDes.startAnimation()
        loadingDes.visibility = View.VISIBLE

    }

    public override fun onResume() {
        super.onResume()
        handler!!.postDelayed(runnable, 1000)
    }

    public override fun onStop() {
        super.onStop()
        handler!!.removeCallbacks(runnable)
    }
}
