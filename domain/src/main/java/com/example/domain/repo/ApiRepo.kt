package com.example.domain.repo

import com.example.domain.model.CoffeeResponse
import retrofit2.Response

interface ApiRepo {
    suspend fun getAllCoffee(): Response<CoffeeResponse>
    suspend fun getProductsByCategory(category: String): Response<CoffeeResponse>

    suspend fun searchProduct(q: String): Response<CoffeeResponse>
}