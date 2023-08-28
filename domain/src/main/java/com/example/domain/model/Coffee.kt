package com.example.domain.model

import java.io.Serializable

data class Coffee(
    val category: String,
    val description: String,
    val image: String,
    val price: Int,
    val rating: Double,
    val title: String
):Serializable