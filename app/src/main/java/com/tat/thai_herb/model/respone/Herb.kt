package com.tat.thai_herb.model.respone

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Herb (
    val data: List<DataList> = arrayListOf()
)

@IgnoreExtraProperties
data class DataList (
    val key: String = "",
    val system: List<SystemElement> = arrayListOf()
)

@IgnoreExtraProperties
data class SystemElement (
    val symptom_name: String = "",
    val symptom_list: List<SymptomList> = arrayListOf()
)

@IgnoreExtraProperties
data class SymptomList (
    val herb_name: String = "",
    val description: String = "",
    val image: String = "",
    val title: String = ""
)