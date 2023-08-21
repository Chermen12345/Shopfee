package com.example.data.api

import com.example.domain.model.CoffeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface CoffeeService {
    @GET("/coffee/all")
    suspend fun getAllCoffee(): Response<CoffeeResponse>
}