package com.tat.thai_herb.listener

import com.tat.thai_herb.model.respone.Detail

interface ActionClickHeader {
    fun onClickList(position: Int)
    fun onClickItem(data: Detail)
}