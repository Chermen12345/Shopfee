package com.example.domain.usecases

import com.example.domain.model.Order
import com.example.domain.repo.FirebaseRepo
import com.example.utils.UiState

class AddOrderUseCase(val repo: FirebaseRepo) {
    suspend fun addOrder(order: Order, result: (UiState) -> Unit){
        repo.addOrder(order,result)
    }
}