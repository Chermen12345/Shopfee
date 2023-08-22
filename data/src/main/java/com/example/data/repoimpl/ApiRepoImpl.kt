package com.example.data.repoimpl

import com.example.data.api.CoffeeService
import com.example.domain.model.CoffeeResponse
import com.example.domain.repo.ApiRepo
import retrofit2.Response

class ApiRepoImpl(val service: CoffeeService): ApiRepo {
    override suspend fun getAllCoffee(): Response<CoffeeResponse> {
        return service.getAllCoffee()
    }

    override suspend fun getProductsByCategory(category: String): Response<CoffeeResponse> {
        return service.getProductsByCategory(category)
    }

    override suspend fun searchProduct(q: String): Response<CoffeeResponse> {
        return service.searchProduct(q)
    }
}