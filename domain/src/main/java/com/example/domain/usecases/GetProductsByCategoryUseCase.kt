package com.example.domain.usecases

import com.example.domain.repo.ApiRepo

class GetProductsByCategoryUseCase(val repo: ApiRepo) {
    suspend fun getProductByCategory(category: String) = repo.getProductsByCategory(category)
}