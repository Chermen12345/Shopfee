package com.example.domain.model

import java.io.Serializable

data class Coffee(
    val category: String="",
    val description:String ="",
    val image: String="",
    val price: Int?=null,
    val rating: Double?=null,
    val title: String=""
):Serializable