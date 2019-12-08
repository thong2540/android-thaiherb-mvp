package com.tat.thai_herb.listener

import com.tat.thai_herb.model.respone.Detail

interface ActionClickHeader {
    fun onClickList(key: String)
    fun onClickItem(data: Detail)
}