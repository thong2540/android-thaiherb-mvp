package com.tat.thai_herb.model.respone

import kotlinx.serialization.SerialName


data class Herb (
    val data: List<DataList>? = null
)

data class DataList (
    val key: String? = null,
    val system: List<SystemElement>? = null
)

data class SystemElement (
    @SerialName("symptom_name")
    val symptomName: String? = null,

    @SerialName("symptom_list")
    val symptomList: List<SymptomList>? = null
)

data class SymptomList (
    @SerialName("herb_name")
    val herbName: String? = null,

    val description: String? = null,
    val image: String? = null,
    val title: String? = null
)