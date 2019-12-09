package com.tat.thai_herb.model.respone

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class SearchList (
    val symptom: String = "",
    val description: String = "",
    val image: String = "",
    val title: String = ""
)