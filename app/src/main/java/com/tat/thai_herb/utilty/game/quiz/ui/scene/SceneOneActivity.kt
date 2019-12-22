package com.tat.thai_herb.utilty.game.quiz.ui.scene

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.tat.thai_herb.utilty.game.quiz.utilty.dialog.DialogGameManager
import com.tat.thai_herb.R
import com.tat.thai_herb.utilty.dialog.DialogInterface
import com.tat.thai_herb.utilty.game.quiz.ui.scene.presenter.SeenePresent
import com.tat.thai_herb.utilty.game.quiz.utilty.SoundType
import com.tat.thai_herb.utilty.game.quiz.utilty.hideStatusBar
import com.tat.thai_herb.utilty.game.quiz.utilty.setSoundButton
import kotlinx.android.synthetic.main.activity_scene_one.*

class SceneOneActivity : AppCompatActivity(),SceneView.View {

    private var presenter: SeenePresent? = null

    private var lList = arrayListOf(
        "ท้องผูก",
        "ไอ,เจ็บคอ",
        "น้ำร้อนลวก",
        "ท้องอืด",
        "ขัดเบา",
        "คลื่นไส้",
        "กลากเกลื้อน",
        "พยาธิลำไส้",
        "กระเพาะอาหาร",
        "ไข้"
    )

    private var quiz: String = ""
    private var ex = 1
    private var score = 0
    private var error = 0
    private var timer: CountDownTimer? = null
    private lateinit var dialogGameManager: DialogGameManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene_one)
        dialogGameManager = DialogGameManager()
        this.hideStatusBar()
        presenter = SeenePresent(this)

        setUpQuiz()
        onEvent()
    }

    fun setUpQuiz() {
        error = 0
        if (ex <= lList.size) {
            quiz = lList[ex - 1]
            title_ex.text = "ข้อที่ $ex"
            text_score_one.text = "score : $score"
            text_quiz.text = quiz

            timer = object : CountDownTimer(30000, 1000) {
                @SuppressLint("SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {
                    text_time_one.text = "${millisUntilFinished / 1000}"
                    if ((millisUntilFinished / 1000) < 11) {
                        setSoundButton(SoundType.TIME)
                    }
                }

                override fun onFinish() {
                    dialogGameManager.alertTime(this@SceneOneActivity)
                    Handler().postDelayed({
                        ex++
                        setUpQuiz()
                    }, 3000)
                }
            }.start()
        } else {
            text_score_one.text = "score : $score"
            Handler().postDelayed({
                showDialogNextStap()
            }, 500)
        }
    }

    private fun onEvent() {
        image_quiz1.setOnClickListener {
            if (quiz == "ท้องอืด") {
                showDialogSuccess(
                    "ขมิ้นชัน",
                    "ใช้แก้อาการท้องอืด ท้องเฟ้อ แน่นจุกเสียด และอาหารไม่ย่อย \n ส่วนที่ใช้ : เหง้าสดและแห้ง"
                )
            } else {
                error++
                checkError()
            }

        }

        image_quiz2.setOnClickListener {
            if (quiz == "ไอ,เจ็บคอ") {
                showDialogSuccess("ขิง", "ใช้แก้อาการไอ และระคายคอจากเสมหะ \n ส่วนที่ใช้ : เหง้าสด")
            } else {
                error++
                checkError()
            }
        }

        image_quiz3.setOnClickListener {
            if (quiz == "กลากเกลื้อน") {
                showDialogSuccess("กระเทียม", "ใช้แก้อาการกลากเกลื้อน \n ส่วนที่ใช้ : หัวใต้ดิน")
            } else {
                error++
                checkError()
            }
        }

        image_quiz4.setOnClickListener {
            if (quiz == "กระเพาะอาหาร") {
                showDialogSuccess(
                    "กล้วยน้ำว้า",
                    "ใช้แก้โรคกระเพาะอาหาร \n ส่วนที่ใช้ : ผลกล้วยดิบ/ห่าม"
                )
            } else {
                error++
                checkError()
            }
        }

        image_quiz5.setOnClickListener {
            if (quiz == "ขัดเบา") {
                showDialogSuccess(
                    "ตะไคร้",
                    "ใช้แก้อาการขัดเบา \n ส่วนที่ใช้ : ลำต้น และเหง้าแก่ สดหรือแห้ง"
                )
            } else {
                error++
                checkError()
            }
        }

        image_quiz6.setOnClickListener {
            if (quiz == "ไข้") {
                showDialogSuccess("บอระเพ็ด", "ใช้แก้อาการไข้ \n ส่วนที่ใช้ : เถาหรือลำต้นสด")
            } else {
                error++
                checkError()
            }
        }

        image_quiz7.setOnClickListener {
            if (quiz == "ท้องผูก") {
                showDialogSuccess(
                    "มะขาม",
                    "ใช้แก้อาการท้องผูก \n ส่วนที่ใช้ : เนื้อฝักแก่ เนื้อเม็ดมะขามแก่"
                )
            } else {
                error++
                checkError()
            }
        }

        image_quiz8.setOnClickListener {
            if (quiz == "น้ำร้อนลวก") {
                showDialogSuccess(
                    "ว่านหางจระเข้",
                    "ใช้แก้แผลไฟไหม้ น้ำร้อนลวก \n ส่วนที่ใช้ : วุ้นจากใบ"
                )
            } else {
                error++
                checkError()
            }
        }

        image_quiz9.setOnClickListener {
            if (quiz == "คลื่นไส้") {
                showDialogSuccess(
                    "ยอ",
                    "ใช้แก้อาการคลื่นไส้ อาเจียน \n ส่วนที่ใช้ : ผลดิบหรือผลห่ามสด"
                )
            } else {
                error++
                checkError()
            }
        }

        image_quiz10.setOnClickListener {
            if (quiz == "พยาธิลำไส้") {
                showDialogSuccess("ฟักทอง", "ใช้แก้โรคพยาธิลำไส้ \n ส่วนที่ใช้ : เมล็ดฟักทองแก่")
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
        dialogGameManager.alertSuccess(
            this,
            title,
            des
        )

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
        //ต่อเชื่อมเก็บคะแนนลง fireBase
        presenter!!.setScoreQuiz(score)
        //
        dialogGameManager.alertQuiz(
            this,
            "คะแนนรวม $score คะแนน",
            false
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

                if (id == R.id.Play) {
                    setSoundButton(SoundType.BUTTON)
                    val intent = Intent(this@SceneOneActivity, SceneTwoActivity::class.java)
                    intent.putExtra("score", score)
                    startActivity(intent)
                    finish()

                }
            }

        })
    }

    override fun isSuccess() {

    }

}
