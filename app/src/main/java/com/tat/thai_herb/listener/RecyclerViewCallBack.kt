package com.tat.thai_herb.listener

import com.tat.thai_herb.model.respone.SymptomList

interface RecyclerViewCallBack {
    fun onClickItem(position: Int)
    fun onPresentData(data: SymptomList)
}