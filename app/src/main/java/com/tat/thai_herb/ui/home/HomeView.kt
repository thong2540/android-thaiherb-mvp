package com.tat.thai_herb.ui.home

import com.tat.thai_herb.model.respone.DataList
import com.tat.thai_herb.utilty.BasePresenter

class HomeView {
    interface View : BasePresenter {
        fun itemDataHerb(item: List<DataList>)
    }
}