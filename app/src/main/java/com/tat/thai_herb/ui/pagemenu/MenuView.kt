package com.tat.thai_herb.ui.pagemenu

import com.tat.thai_herb.model.respone.UserInfo

class MenuView {
    interface View {
        fun responeUserInfo(dataUser: UserInfo)
    }
}