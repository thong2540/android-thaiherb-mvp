package com.tat.thai_herb.utilty.game.quiz.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tat.thai_herb.R
import com.tat.thai_herb.utilty.game.quiz.ui.scene.MemorySceneOneActivity
import com.tat.thai_herb.utilty.game.quiz.utilty.SoundType
import com.tat.thai_herb.utilty.game.quiz.utilty.hideStatusBar
import com.tat.thai_herb.utilty.game.quiz.utilty.setSoundButton
import kotlinx.android.synthetic.main.activity_main_memory.*

class MainMemoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_memory)
        this.hideStatusBar()

        onEvent()

    }

    private fun onEvent() {
        open_memory.setOnClickListener {
            this.setSoundButton(SoundType.BUTTON)
            val intent = Intent(this, MemorySceneOneActivity::class.java)
            startActivity(intent)
        }

        logout_memory.setOnClickListener {
            this.setSoundButton(SoundType.BUTTON)
            finish()
        }
    }
}
