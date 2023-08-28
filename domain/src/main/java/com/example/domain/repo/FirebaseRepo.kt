package com.example.domain.repo

import com.example.domain.model.Order
import com.example.utils.UiState

interface FirebaseRepo {
    suspend fun addOrder(
        order: Order, result: (UiState)->Unit)
}