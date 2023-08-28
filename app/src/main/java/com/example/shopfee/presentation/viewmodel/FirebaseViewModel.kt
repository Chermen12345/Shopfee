package com.example.shopfee.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Order
import com.example.domain.usecases.AddOrderUseCase
import com.example.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FirebaseViewModel(val useCase: AddOrderUseCase): ViewModel() {

    val stateOfAddingOrder: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading())
    fun addOrder(order: Order) = viewModelScope.launch {

        useCase.addOrder(order){
            stateOfAddingOrder.value = it
        }


    }
}