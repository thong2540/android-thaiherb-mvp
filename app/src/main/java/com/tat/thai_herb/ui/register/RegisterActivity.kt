package com.tat.thai_herb.ui.register

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.senate_system.tracking_android.ui.dialog.DialogManager
import com.tat.thai_herb.R
import com.tat.thai_herb.ui.register.persenter.RegisterPersenter
import com.tat.thai_herb.utilty.StatusbarManager
import com.tat.thai_herb.utilty.dialog.DialogInterface
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterView.View {

    private lateinit var presenter: RegisterPersenter
    private var mPartImage: String = "default"

    private lateinit var dialogManager: DialogManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        StatusbarManager.darkenStatusBar(this)
        presenter = RegisterPersenter(this)

        onEvent()

    }

    private fun onEvent() {
        imageView_profile.setOnClickListener {
            TedImagePicker.with(this)
                .title("เลือกรูปภาพ")
                .start { uri -> showSingleImage(uri) }
        }

        btnRegister.setOnClickListener {
            if (checkValidate()) {
                presenter.register(
                    editTextNameRegister.text.toString(),
                    editTextEmailRegister.text.toString(),
                    editTextPasswordRegister.text.toString(),
                    mPartImage
                )
            }
        }

        txtLoginRegister.setOnClickListener {
            finish()
        }
    }

    private fun showSingleImage(uri: Uri) {
        presenter.uploadImage(uri)
        Glide.with(this).load(uri).into(imageView_profile)
    }

    private fun checkValidate(): Boolean {
        if (editTextNameRegister.text.toString().isEmpty()) {
            editTextNameRegister.requestFocus()
            editTextNameRegister.error = "กรุณาระบุชื่อ"
            return false
        } else if (editTextEmailRegister.text.toString().isEmpty()) {
            editTextEmailRegister.requestFocus()
            editTextEmailRegister.error = "กรุณาระบุอีเมล"
            return false
        } else if (editTextPasswordRegister.text.toString().isEmpty() || editTextPasswordRegister.text.toString().length < 6
        ) {
            editTextPasswordRegister.requestFocus()
            editTextPasswordRegister.error = "กรุณาระบุรหัสผ่านขั้นต่ำ 6 ตัว"
            return false
        } else if (editTextConPasswordRegister.text.toString().isEmpty() || editTextConPasswordRegister.text.toString() != editTextPasswordRegister.text.toString()) {
            editTextConPasswordRegister.requestFocus()
            editTextConPasswordRegister.error = "กรุณาระบุรหัสผ่านให้ถูกต้อง"
            return false
        } else {
            return true
        }
    }

    override fun saveImageSuccess(partImage: String) {
        mPartImage = partImage
    }

    override fun registerSuccess() {
        dialogManager = DialogManager()
        dialogManager.alertAlert(
            this,
            "แจ้งเตือน",
            "สมัครสมาชิกสำเร็จ",
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

    override fun saveImageFail(e: Exception) {
        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showeLoding() {
        loadingRegister.startAnimation()
        loadingRegister.visibility = View.VISIBLE

    }

    override fun hideLoding() {
        loadingRegister.clearAnimation()
        loadingRegister.visibility = View.GONE
    }
}
