package com.tat.thai_herb.ui.login.persenter

import com.google.firebase.auth.FirebaseAuth
import com.tat.thai_herb.ui.login.LoginView

class LoginPersenter(private val view: LoginView.View) {

    private var firebaseAuth: FirebaseAuth? = null

    init {
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun setLogin(email: String, password: String) {
        view.showeLoding()
        firebaseAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    view.hideLoding()
                    view.isSuccess()
                } else {
                    view.onError(it.exception.toString())
                }
            }
    }
}