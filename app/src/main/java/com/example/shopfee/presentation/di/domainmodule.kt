package com.example.shopfee.presentation.di

import com.example.domain.usecases.GetAllCoffeeUseCase
import com.example.domain.usecases.GetProductsByCategoryUseCase
import com.example.domain.usecases.SearchProductUseCase
import org.koin.dsl.module

val domainmodule = module {
    single<GetAllCoffeeUseCase>{
        GetAllCoffeeUseCase(repo = get())

    }
    single<GetProductsByCategoryUseCase>{
        GetProductsByCategoryUseCase(repo = get())

    }
    single<SearchProductUseCase> {
        SearchProductUseCase(repo = get())
    }
}