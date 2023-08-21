package com.example.data.repoimpl

import com.example.data.api.CoffeeService
import com.example.domain.model.CoffeeResponse
import com.example.domain.repo.ApiRepo
import retrofit2.Response

class ApiRepoImpl(val service: CoffeeService): ApiRepo {
    override suspend fun getAllCoffee(): Response<CoffeeResponse> {
        return service.getAllCoffee()
    }
}