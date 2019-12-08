package com.tat.thai_herb.ui.detaimain

import com.tat.thai_herb.model.respone.Detail
import com.tat.thai_herb.utilty.BasePresenter

class DetailMainView {
    interface View : BasePresenter {
        fun responeHeader(listHeader: List<String>)
        fun responeList(listData: List<Detail>)
    }
}