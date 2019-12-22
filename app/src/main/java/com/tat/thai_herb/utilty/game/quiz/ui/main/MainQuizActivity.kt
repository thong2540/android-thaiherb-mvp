package com.tat.thai_herb.utilty.game.quiz.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tat.thai_herb.utilty.game.quiz.utilty.dialog.DialogGameManager
import com.tat.thai_herb.R
import com.tat.thai_herb.utilty.game.quiz.ui.scene.SceneOneActivity
import com.tat.thai_herb.utilty.game.quiz.utilty.SoundType
import com.tat.thai_herb.utilty.game.quiz.utilty.hideStatusBar
import com.tat.thai_herb.utilty.game.quiz.utilty.setSoundButton
import kotlinx.android.synthetic.main.activity_main_quiz.*


class MainQuizActivity : AppCompatActivity() {

    private lateinit var dialogGameManager: DialogGameManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_quiz)
        this.hideStatusBar()
        dialogGameManager = DialogGameManager()
//        this.setSoundButton(SoundType.MAIN)
        onEvent()

    }

    private fun onEvent() {
        open_quiz.setOnClickListener {
            this.setSoundButton(SoundType.BUTTON)
            val intent = Intent(this, SceneOneActivity::class.java)
//            val intent = Intent(this, MainMemoryActivity::class.java)
            startActivity(intent)
        }

        logout.setOnClickListener {
            this.setSoundButton(SoundType.BUTTON)
            finish()
        }
    }
}
