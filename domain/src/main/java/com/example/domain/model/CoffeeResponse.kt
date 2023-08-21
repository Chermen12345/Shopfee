package com.example.domain.model

data class CoffeeResponse(
    val coffees: List<Coffee>,
    val message: Any,
    val success: Boolean
)