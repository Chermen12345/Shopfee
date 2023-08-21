package com.example.categories.di

import com.example.categories.adapters.ProductAdapter
import com.example.categories.viewmodel.CategoryViewModel
import org.koin.dsl.module

val categorymodule = module {
    single<CategoryViewModel>{
        CategoryViewModel(getAllCoffeeUseCase = get())
    }
    factory<ProductAdapter> {
        ProductAdapter()
    }
}