package com.tat.thai_herb.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.senate_system.tracking_android.ui.dialog.DialogManager
import com.tat.thai_herb.R
import com.tat.thai_herb.extensions.hideKeyboard
import com.tat.thai_herb.ui.login.persenter.LoginPersenter
import com.tat.thai_herb.ui.main.MainActivity
import com.tat.thai_herb.ui.register.RegisterActivity
import com.tat.thai_herb.ui.resetpassword.ResetPassword
import com.tat.thai_herb.utilty.ActivityTransition
import com.tat.thai_herb.utilty.StatusbarManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView.View {

    private lateinit var presenter: LoginPersenter
    private lateinit var dialogManager: DialogManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        StatusbarManager.darkenStatusBar(this)
        presenter = LoginPersenter(this)
        dialogManager = DialogManager()

        onEvent()
    }

    private fun onEvent() {
        txtRegisLogin.setOnClickListener {
            val inten = Intent(this, RegisterActivity::class.java)
            startActivity(inten)
        }

        btnLogin.setOnClickListener {
            if (checkValidate()) {
                presenter.setLogin(
                    editTextEmailLogin.text.toString(),
                    editTextPasswordLogin.text.toString()
                )
                this.hideKeyboard()
            }
        }

        txtForgetLogin.setOnClickListener {
            val intent = Intent(this, ResetPassword::class.java)
            startActivity(intent)
        }

    }

    private fun checkValidate(): Boolean {
        if (editTextEmailLogin.text.toString().isEmpty()) {
            editTextEmailLogin.requestFocus()
            editTextEmailLogin.error = "กรุณาระบุอีเมล"
            return false
        } else if (editTextPasswordLogin.text.toString().isEmpty()) {
            editTextPasswordLogin.requestFocus()
            editTextPasswordLogin.error = "กรุณาระบุรหัสผ่าน"
            return false
        } else {
            return true
        }
    }

    override fun isSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        ActivityTransition.GoLeft(this)
        finish()
    }

    override fun showeLoding() {
        loadingLogin.startAnimation()
        loadingLogin.visibility = View.VISIBLE
    }

    override fun hideLoding() {
        loadingLogin.clearAnimation()
        loadingLogin.visibility = View.GONE
    }

    override fun onError(message: String) {
        dialogManager.alertAlert(
            this,
            "แจ้งเตือน",
            message,
            "ตกลง"
        )
    }
}
