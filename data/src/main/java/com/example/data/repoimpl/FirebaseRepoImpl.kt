package com.example.data.repoimpl

import com.example.domain.model.Order
import com.example.domain.repo.FirebaseRepo
import com.example.utils.Consts.AUTH
import com.example.utils.Consts.FIRESTORE
import com.example.utils.UiState

class FirebaseRepoImpl(): FirebaseRepo {
    override suspend fun addOrder(order: Order, result: (UiState) -> Unit) {
        FIRESTORE.collection("users").document(AUTH.currentUser!!.uid).collection("addedOrders").add(order)
    }

}