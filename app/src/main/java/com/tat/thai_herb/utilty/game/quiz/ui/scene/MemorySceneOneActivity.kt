package com.tat.thai_herb.utilty.game.quiz.ui.scene

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tat.thai_herb.R
import com.tat.thai_herb.utilty.dialog.DialogInterface
import com.tat.thai_herb.utilty.game.quiz.ui.scene.presenter.SeenePresent
import com.tat.thai_herb.utilty.game.quiz.utilty.RandomList
import com.tat.thai_herb.utilty.game.quiz.utilty.SoundType
import com.tat.thai_herb.utilty.game.quiz.utilty.dialog.DialogGameManager
import com.tat.thai_herb.utilty.game.quiz.utilty.hideStatusBar
import com.tat.thai_herb.utilty.game.quiz.utilty.setSoundButton
import kotlinx.android.synthetic.main.activity_memory_scene_one.*

class MemorySceneOneActivity : AppCompatActivity(),SceneView.View {

    private var presenter: SeenePresent? = null

    private lateinit var dialogGameManager: DialogGameManager

    private var lList = arrayListOf(
        R.drawable.memory1,
        R.drawable.memory2,
        R.drawable.memory3,
        R.drawable.memory4,
        R.drawable.memory5
    )

    private var list2 = arrayListOf(
        R.drawable.memory6,
        R.drawable.memory7,
        R.drawable.memory8,
        R.drawable.memory9,
        R.drawable.memory10
    )

    private var list: List<Int> = arrayListOf()
    private var mCount = 1
    private var itemCount = 5
    private var score = 0
    private var mId = 0
    private var mView: ImageView? = null
    private var mId2 = 0
    private var mView2: ImageView? = null
    private val def = R.drawable.bg_game_de
    private var isHinden: Boolean = false
    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_scene_one)
        dialogGameManager = DialogGameManager()
        this.hideStatusBar()
        presenter = SeenePresent(this)
        list = RandomList.renDomItem(lList, 10)
        score_memory_one.text = "score : $score"
        showCount.text = "จำนวนข้อที่เหลือ : $itemCount"

        setTimer()
        onEvent()


    }

    private fun setTimer() {
        timer = object : CountDownTimer(60000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                memory_time_one.text = "${millisUntilFinished / 1000}"
                if ((millisUntilFinished / 1000) < 11) {
                    setSoundButton(SoundType.TIME)
                }
            }

            override fun onFinish() {
                Toast.makeText(
                    applicationContext, "หมดเวลาแล้วจ้าาา !!",
                    Toast.LENGTH_LONG
                ).show()
//                dialogGameManager.alertTime(this@MemorySceneOneActivity)
                Handler().postDelayed({
                    showDialogNextStap()
                }, 3000)
            }
        }.start()

    }

    private fun onEvent() {
        mem1.setOnClickListener {
            showView(it as ImageView, list[0])
        }

        mem2.setOnClickListener {
            showView(it as ImageView, list[1])
        }

        mem3.setOnClickListener {
            showView(it as ImageView, list[2])
        }

        mem4.setOnClickListener {
            showView(it as ImageView, list[3])
        }

        mem5.setOnClickListener {
            showView(it as ImageView, list[4])
        }

        mem6.setOnClickListener {
            showView(it as ImageView, list[5])
        }

        mem7.setOnClickListener {
            showView(it as ImageView, list[6])
        }

        mem8.setOnClickListener {
            showView(it as ImageView, list[7])
        }

        mem9.setOnClickListener {
            showView(it as ImageView, list[8])
        }

        mem10.setOnClickListener {
            showView(it as ImageView, list[9])
        }

    }

    private fun showView(view: ImageView, part: Int) {
        setSoundButton(SoundType.BUTTON)
        view.setImageResource(part)
        if (mCount < 2) {
            mView = view
            mId = part
            mCount++
        } else {
            mView2 = view
            mId2 = part
            // Check view
            Handler().postDelayed({
                if (mId == mId2) {
                    mView!!.visibility = View.INVISIBLE
                    mView2!!.visibility = View.INVISIBLE
                    itemCount--
                    score += 2
                    score_memory_one.text = "score : $score"
                    showCount.text = "จำนวนคู่ที่เหลือ : $itemCount"
                    showDialogSuccess(mId, itemCount)
                } else {
                    mView!!.setImageResource(def)
                    mView2!!.setImageResource(def)
                }
            }, 800)
            mCount = 1
        }
    }

    private fun showDialogSuccess(part: Int, count: Int) {
        this.setSoundButton(SoundType.CORRECT)
        var title: String
        var des: String

        when (part) {
            R.drawable.memory1 -> {
                title = "กานพลู"
                des = "ใช้แก้อาการท้องอืด ท้องเฟ้อ และแน่นจุกเสียด \n ส่วนที่ใช้ : ดอกตูม"
            }
            R.drawable.memory2 -> {
                title = "สะเดาบ้าน"
                des = "ใช้แก้อาการเบื่ออาหาร \n ส่วนที่ใช้ : ยอดและดอกสะเดา"
            }
            R.drawable.memory3 -> {
                title = "แก้ว"
                des = "ใช้แก้อาการปวดฟัน \n ส่วนที่ใช้ : ใบสด"
            }
            R.drawable.memory4 -> {
                title = "มะคำดีควาย"
                des = "ใช้แก้โรคชันนะตุ \n ส่วนที่ใช้ : ผลแก่"
            }
            R.drawable.memory5 -> {
                title = "พลู"
                des = "ใช้แก้อาการลมพิษ \n ส่วนที่ใช้ : ใบสด"
            }
            R.drawable.memory6 -> {
                title = "พญายอ"
                des = "ใช้แก้อาการเริม งูสวัด \n ส่วนที่ใช้ : ใบ"
            }
            R.drawable.memory7 -> {
                title = "ผักบุ้งทะเล"
                des = "ใช้แก้อาการแพ้/อักเสบจากแมลงสัตว์กัดต่อย \n ส่วนที่ใช้ : ใบและเถาสด"
            }
            R.drawable.memory8 -> {
                title = "เทียนบ้าน"
                des = "ใช้แก้โรคฝี/แผลพุพอง \n ส่วนที่ใช้ : ใบสดและดอกสด"
            }
            R.drawable.memory9 -> {
                title = "ขี้เหล็ก"
                des = "ใช้แก้อาการนอนไม่หลับ \n ส่วนที่ใช้ : ใบและดอก"
            }
            else -> {
                title = "น้อยหน่า"
                des = "ใช้แก้โรคเหา \n ส่วนที่ใช้ : ใบสดและเมล็ด"
            }
        }

        dialogGameManager.alertSuccess(
            this,
            title,
            des
        )

        dialogGameManager.setOnDialogClickListener(object : DialogInterface.DialogCallBackListener {
            override fun onClickButton(id: Int) {
                if (id == R.id.clickSuccess) {
                    setSoundButton(SoundType.BUTTON)
                    if (count == 0) {
                        timer!!.cancel()
                        Handler().postDelayed({
                            showDialogNextStap()
                        }, 500)
                    }
                }
            }
        })
    }

    private fun showDialogNextStap() {
        presenter!!.setScoreMemory(score)
        //ต่อเชื่อมเก็บคะแนนลง fireBase
        dialogGameManager.alertQuiz(
            this,
            "คะแนนรวม $score คะแนน",
            isHinden
        )

        dialogGameManager.setOnDialogClickListener(object : DialogInterface.DialogCallBackListener {
            override fun onClickButton(id: Int) {
                if (id == R.id.Restart) {
                    setSoundButton(SoundType.BUTTON)
                    score = 0
                    list = RandomList.renDomItem(lList, 10)
                    showView()
                    isHinden = false
                }

                if (id == R.id.Home) {
                    setSoundButton(SoundType.BUTTON)
                    finish()
                }

                if (id == R.id.Play) {
                    setSoundButton(SoundType.BUTTON)
                    list = RandomList.renDomItem(list2, 10)
                    showView()
                    isHinden = true
                }
            }
        })
    }

    private fun showView() {
        itemCount = 5
        mCount = 1

        score_memory_one.text = "score : $score"
        showCount.text = "จำนวนข้อที่เหลือ : $itemCount"

        mem1.setImageResource(def)
        mem2.setImageResource(def)
        mem3.setImageResource(def)
        mem4.setImageResource(def)
        mem5.setImageResource(def)
        mem6.setImageResource(def)
        mem7.setImageResource(def)
        mem8.setImageResource(def)
        mem9.setImageResource(def)
        mem10.setImageResource(def)

        mem1.visibility = View.VISIBLE
        mem2.visibility = View.VISIBLE
        mem3.visibility = View.VISIBLE
        mem4.visibility = View.VISIBLE
        mem5.visibility = View.VISIBLE
        mem6.visibility = View.VISIBLE
        mem7.visibility = View.VISIBLE
        mem8.visibility = View.VISIBLE
        mem9.visibility = View.VISIBLE
        mem10.visibility = View.VISIBLE

        setTimer()
    }

    override fun isSuccess() {

    }
}
