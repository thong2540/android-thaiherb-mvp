package com.tat.thai_herb.ui.splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.senate_system.tracking_android.ui.dialog.DialogManager
import com.tat.thai_herb.R
import com.tat.thai_herb.ui.login.LoginActivity
import com.tat.thai_herb.ui.main.MainActivity
import com.tat.thai_herb.utilty.ActivityTransition
import com.tat.thai_herb.utilty.StatusbarManager
import com.tat.thai_herb.utilty.dialog.DialogInterface
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private lateinit var dialogManager: DialogManager

    private var firebaseUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        StatusbarManager.darkenStatusBar(this)
        firebaseUser = FirebaseAuth.getInstance().currentUser
        handler = Handler()

        checkInternet()

        setInit()
    }

    @SuppressLint("ServiceCast")
    private fun checkInternet() {
        val cm = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            if (firebaseUser != null) {
                startMain()
            } else {
                startLogin()
            }
        }else{
            dialogManager = DialogManager()
            dialogManager.alertAlert(
                this,
                "แจ้งเตือน",
                "ไม่สามรถเชื่อมต่ออินเตอร์เน็ตได้ กรุณาตรวจสอบเครือข่าย",
                "ตกลง"
            )
            dialogManager.setOnDialogClickListener(object : DialogInterface.DialogCallBackListener {
                override fun onClickButton(id: Int) {
                    if (id == R.id.textConfirm) {
                        finish()
                    }
                }
            })
        }
    }

    private fun setInit() {
        val myanim = AnimationUtils.loadAnimation(this, R.anim.mytra)
        imgLogoAppSplash.startAnimation(myanim)
    }


    private fun startMain() {
        runnable = Runnable {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            ActivityTransition.GoLeft(this@SplashActivity)
            finish()
        }
    }

    private fun startLogin() {
        runnable = Runnable {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            ActivityTransition.GoLeft(this@SplashActivity)
            finish()
        }
    }

    public override fun onResume() {
        super.onResume()
        handler!!.postDelayed(runnable, 3000)
    }

    public override fun onStop() {
        super.onStop()
        handler!!.removeCallbacks(runnable)
    }
}
