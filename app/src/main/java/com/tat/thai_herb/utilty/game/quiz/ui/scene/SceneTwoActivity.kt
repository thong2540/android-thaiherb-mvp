package com.tat.thai_herb.utilty.game.quiz.ui.scene

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import com.tat.thai_herb.utilty.game.quiz.utilty.dialog.DialogGameManager
import com.tat.thai_herb.R
import com.tat.thai_herb.utilty.dialog.DialogInterface
import com.tat.thai_herb.utilty.game.quiz.ui.scene.presenter.SeenePresent
import com.tat.thai_herb.utilty.game.quiz.utilty.SoundType
import com.tat.thai_herb.utilty.game.quiz.utilty.hideStatusBar
import com.tat.thai_herb.utilty.game.quiz.utilty.setSoundButton
import kotlinx.android.synthetic.main.activity_scene_two.*

class SceneTwoActivity : AppCompatActivity(),SceneView.View {

    private var presenter: SeenePresent? = null

    private var lList = arrayListOf(
        "โรคเหา",
        "พยาธิลำไส้",
        "เคล็ดขัดยอก",
        "กลากเกลื้อน",
        "ท้องอืด",
        "ชันนะตุ",
        "ท้องเสีย",
        "ขัดเบา",
        "ไอ,เจ็บคอ",
        "เบื่ออาหาร"
    )

    private var quiz: String = ""
    private var ex = 1
    private var score = 0
    private var error = 0
    private var timer: CountDownTimer? = null
    private lateinit var dialogGameManager: DialogGameManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene_two)
        dialogGameManager = DialogGameManager()
        this.hideStatusBar()
        presenter = SeenePresent(this)
        if (intent == null) return

        setUpQuiz()
        onEvent()
    }

    fun setUpQuiz() {
        error = 0
        if (ex <= lList.size) {
            quiz = lList[ex - 1]
            title_ex_two.text = "ข้อที่ $ex"
            text_score_two.text = "score : $score"
            text_quiz_two.text = quiz

            timer = object : CountDownTimer(30000, 1000) {
                @SuppressLint("SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {
                    text_time_two.text = "${millisUntilFinished / 1000}"
                    if ((millisUntilFinished / 1000) < 11) {
                        setSoundButton(SoundType.TIME)
                    }
                }

                override fun onFinish() {
                    dialogGameManager.alertTime(this@SceneTwoActivity)
                    Handler().postDelayed({
                        ex++
                        setUpQuiz()
                    }, 3000)
                }
            }.start()
        } else {
            text_score_two.text = "score : $score"
            Handler().postDelayed({
                showDialogNextStap()
            }, 500)

        }
    }

    private fun onEvent() {
        image_quiz1_two.setOnClickListener {
            if (quiz == "ท้องเสีย") {
                showDialogSuccess(
                    "มังคุด",
                    "ใช้แก้อาการท้องเสีย \n ส่วนที่ใช้ : เปลือกผลแห้ง"
                )
            } else {
                error++
                checkError()
            }

        }

        image_quiz2_two.setOnClickListener {
            if (quiz == "กลากเกลื้อน") {
                showDialogSuccess("ข่า", "ใช้แก้อาการกลากเกลื้อน \n ส่วนที่ใช้ : เหง้าแก่สดหรือแห้ง")
            } else {
                error++
                checkError()
            }
        }

        image_quiz3_two.setOnClickListener {
            if (quiz == "ไอ,เจ็บคอ") {
                showDialogSuccess("ดีปลี", "ใช้แก้อาการไอ และระคายคอจากเสมหะ \n ส่วนที่ใช้ : ผลแก่แห้ง")
            } else {
                error++
                checkError()
            }
        }

        image_quiz4_two.setOnClickListener {
            if (quiz == "ท้องอืด") {
                showDialogSuccess(
                    "กระชาย",
                    "ใช้แก้อาการท้องอืด ท้องเฟ้อ และแน่นจุกเสียด \n ส่วนที่ใช้ : เหง้าใต้ดินและราก"
                )
            } else {
                error++
                checkError()
            }
        }

        image_quiz5_two.setOnClickListener {
            if (quiz == "เบื่ออาหาร") {
                showDialogSuccess(
                    "มะระขี้นก",
                    "ใช้แก้อาการเบื่ออาหาร \n ส่วนที่ใช้ : เนื้อผลอ่อน และลูก"
                )
            } else {
                error++
                checkError()
            }
        }

        image_quiz6_two.setOnClickListener {
            if (quiz == "พยาธิลำไส้") {
                showDialogSuccess("มะหาด", "ใช้แก้โรคพยาธิลำไส้ \n ส่วนที่ใช้ : แก่นต้นมะหาด")
            } else {
                error++
                checkError()
            }
        }

        image_quiz7_two.setOnClickListener {
            if (quiz == "ชันนะตุ") {
                showDialogSuccess(
                    "มะคำดีควาย",
                    "ใช้แก้โรคชันนะตุ \n ส่วนที่ใช้ : ผลแก่"
                )
            } else {
                error++
                checkError()
            }
        }

        image_quiz8_two.setOnClickListener {
            if (quiz == "ขัดเบา") {
                showDialogSuccess(
                    "สับปะรด",
                    "ใช้แก้อาการขัดเบา \n ส่วนที่ใช้ : เหง้าทั้งสดและแห้ง"
                )
            } else {
                error++
                checkError()
            }
        }

        image_quiz9_two.setOnClickListener {
            if (quiz == "เคล็ดขัดยอก") {
                showDialogSuccess(
                    "ไพล",
                    "ใช้แก้อาการเคล็ดขัดยอก \n ส่วนที่ใช้ : เหง้าแก่"
                )
            } else {
                error++
                checkError()
            }
        }

        image_quiz10_two.setOnClickListener {
            if (quiz == "โรคเหา") {
                showDialogSuccess("น้อยหน่า", "ใช้แก้โรคเหา \n ส่วนที่ใช้ : ใบสดและเมล็ด")
            } else {
                error++
                checkError()
            }
        }

    }

    private fun checkError() {
        this.setSoundButton(SoundType.WRONG)
        if (error == 1) {
            showDialogError("ตอบผิดจ้า ตอบใหม่ได้อีก 2 ครั้ง")
        } else if (error == 2) {
            showDialogError("ตอบผิดจ้า ตอบใหม่ได้อีก 1 ครั้ง")
        } else if (error == 3) {
            showDialogError("ว้าแย่จังตอบไม่ถูกเลย ไปข้อใหม่กันเลย !")
            timer!!.cancel()
            Handler().postDelayed({
                score += (3 - error)
                ex++
                setUpQuiz()
            }, 2000)
        }
    }

    private fun showDialogError(title: String) {
        dialogGameManager.alertError(
            this,
            title
        )
    }

    private fun showDialogSuccess(title: String, des: String) {
        this.setSoundButton(SoundType.CORRECT)
        timer!!.cancel()

        Handler().postDelayed({
            dialogGameManager.alertSuccess(
                this,
                title,
                des
            )
        }, 800)

        dialogGameManager.setOnDialogClickListener(object : DialogInterface.DialogCallBackListener {
            override fun onClickButton(id: Int) {
                if (id == R.id.clickSuccess) {
                    setSoundButton(SoundType.BUTTON)
                    score += (3 - error)
                    ex++
                    setUpQuiz()
                }
            }

        })
    }

    private fun showDialogNextStap() {
        val  intent = intent
        val mScore = intent.getIntExtra("score",0)

        presenter!!.setScoreQuiz(score + mScore)

        dialogGameManager.alertQuiz(
            this,
            "คะแนนรวม ${score + mScore} คะแนน",
            true
        )

        dialogGameManager.setOnDialogClickListener(object : DialogInterface.DialogCallBackListener {
            override fun onClickButton(id: Int) {
                if (id == R.id.Restart) {
                    setSoundButton(SoundType.BUTTON)
                    ex = 1
                    score = 0
                    setUpQuiz()
                }

                if (id == R.id.Home) {
                    setSoundButton(SoundType.BUTTON)
                    finish()
                }
            }

        })
    }

    override fun isSuccess() {

    }
}
