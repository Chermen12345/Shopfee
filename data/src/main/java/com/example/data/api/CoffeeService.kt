package com.example.data.api

import com.example.domain.model.CoffeeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoffeeService {
    @GET("/coffee/all")
    suspend fun getAllCoffee(): Response<CoffeeResponse>
    @GET("/coffee/all/bycategory")
    suspend fun getProductsByCategory(@Query("category") category: String): Response<CoffeeResponse>

    @GET("/coffee/all/search")
    suspend fun searchProduct(@Query("q")q: String): Response<CoffeeResponse>
}