package com.example.utils

sealed class UiState(val message: String?=null){
    class Success(message: String): UiState(message = message)
    class Loading(): UiState()
    class Error(message: String): UiState(message = message)
}
