package com.tat.thai_herb.ui.register

import com.tat.thai_herb.utilty.BasePresenter

class RegisterView {

    interface View : BasePresenter {
        fun saveImageSuccess(partImage: String)
        fun saveImageFail(e: Exception)
        fun registerSuccess()
    }
}