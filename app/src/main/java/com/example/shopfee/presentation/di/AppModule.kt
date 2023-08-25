package com.example.shopfee.presentation.di


import com.example.shopfee.presentation.fragments.HomeFragment
import com.example.shopfee.presentation.viewmodel.AppViewModel
import org.koin.dsl.module

val appmodule = module {
    factory<AppViewModel> {
        AppViewModel(searchProductUseCase = get())
    }

}