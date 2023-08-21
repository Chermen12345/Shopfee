package com.example.domain.usecases

import com.example.domain.repo.ApiRepo

class GetAllCoffeeUseCase(val repo: ApiRepo) {
    suspend fun getAllCoffee() = repo.getAllCoffee()
}