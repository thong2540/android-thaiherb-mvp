package com.tat.thai_herb.ui.editProfile

import com.tat.thai_herb.utilty.BasePresenter

class EditProfileView {
    interface View : BasePresenter {
        fun onSuccess(part: String,name: String)
    }
}