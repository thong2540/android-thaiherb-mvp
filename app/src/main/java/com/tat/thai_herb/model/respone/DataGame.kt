package com.tat.thai_herb.model.respone

data class DataGame(
    val symptom: String = "",
    val description: String = "",
    val part: Int,
    val image: String = "",
    val title: String = ""
)