package com.tat.thai_herb.ui.resetpassword

class ResetPasswordView {
    interface View {
        fun isSuccess(isSuccess: Boolean)
        fun isError(message: String)
    }
}