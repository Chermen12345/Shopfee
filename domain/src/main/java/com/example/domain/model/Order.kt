package com.example.domain.model

data class Order(
    val coffee: Coffee?=null,
    val variant: String="",
    val size: String="",
    val sugar:Boolean=false,
    val ice: Boolean=false
)
