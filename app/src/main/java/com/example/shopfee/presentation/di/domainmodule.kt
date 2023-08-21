package com.example.shopfee.presentation.di

import com.example.domain.usecases.GetAllCoffeeUseCase
import org.koin.dsl.module

val domainmodule = module {
    single<GetAllCoffeeUseCase>{
        GetAllCoffeeUseCase(repo = get())

    }
}