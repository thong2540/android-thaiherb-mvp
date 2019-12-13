package com.tat.thai_herb.ui.resetpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.senate_system.tracking_android.ui.dialog.DialogManager
import com.tat.thai_herb.R
import com.tat.thai_herb.extensions.darkenStatusBarColor
import com.tat.thai_herb.ui.resetpassword.presenter.ResetPasswordPresenter
import com.tat.thai_herb.utilty.dialog.DialogInterface
import kotlinx.android.synthetic.main.activity_reset_password.*
import kotlinx.android.synthetic.main.header_toolbar_main.view.*

class ResetPassword : AppCompatActivity(), ResetPasswordView.View {

    private lateinit var presenter: ResetPasswordPresenter
    private lateinit var dialogManager: DialogManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        this.darkenStatusBarColor(R.drawable.bg_toolbar)
        presenter = ResetPasswordPresenter(this)

        setUpToolbar()
        onEvent()
    }

    private fun setUpToolbar() {
        heard_resetPassword.textViewMain.text = "แก้ไขรหัสผ่าน"
        heard_resetPassword.clear_action.visibility = View.VISIBLE

    }

    private fun onEvent() {
        heard_resetPassword.clear_action.setOnClickListener {
            finish()
        }

        btn_reset.setOnClickListener {
            presenter.postReset(send_email.text.toString().trim())
        }

    }

    override fun isSuccess(isSuccess: Boolean) {
        dialogManager = DialogManager()
        dialogManager.alertAlert(
            this,
            "แจ้งเตือน",
            "กรุณาเข้าไปยังอีเมลที่ท่านระบุเพื่อนทำการ ResetPassword !",
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

    override fun isError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
