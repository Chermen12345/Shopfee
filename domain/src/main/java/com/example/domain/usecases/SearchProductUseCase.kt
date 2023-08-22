package com.example.domain.usecases

import com.example.domain.repo.ApiRepo

class SearchProductUseCase(val repo: ApiRepo) {
    suspend fun searchProduct(q: String) = repo.searchProduct(q)
}