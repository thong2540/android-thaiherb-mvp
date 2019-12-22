package com.tat.thai_herb.model.respone

import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
data class UserInfo(
    val username: String = "",
    val email: String = "",
    val imgURL: String = "",
    val id: String = "",
    val scoreFirst: Int = 0,
    val scoreSecond: Int = 0,
    val scoreThird: Int = 0

)