package com.tat.thai_herb.model.request

data class User(
    val username: String,
    val email: String,
    val imgURL: String,
    val id: String,
    val scoreFirst: String,
    val scoreSecond: String,
    val scoreThird: String

)