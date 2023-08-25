package com.example.shopfee.presentation.di

import com.example.shopfee.presentation.viewmodel.CategoryViewModel
import org.koin.dsl.module

val categorymodule = module {
    single<CategoryViewModel>{
        CategoryViewModel(getAllCoffeeUseCase = get(), getProductsByCategoryUseCase = get())
    }
    factory <com.example.shopfee.presentation.adapters.ProductAdapter>{
        com.example.shopfee.presentation.adapters.ProductAdapter()
    }

}