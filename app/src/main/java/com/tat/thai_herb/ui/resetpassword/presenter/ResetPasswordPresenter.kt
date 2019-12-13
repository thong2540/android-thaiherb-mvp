package com.tat.thai_herb.ui.resetpassword.presenter

import com.google.firebase.auth.FirebaseAuth
import com.tat.thai_herb.ui.resetpassword.ResetPasswordView

class ResetPasswordPresenter(private val view: ResetPasswordView.View) {

    private var firebaseAuth: FirebaseAuth? = null

    init {
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun postReset(email: String ){
        if (email.isEmpty()){
            view.isError("ท่านยังไม่ได้ระบุอีเมล !")
        }else{
            firebaseAuth!!.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    view.isSuccess(task.isSuccessful)
                } else {
                    val error = task.exception!!.message
                    view.isError(error.toString())
                }
            }

        }
    }
}