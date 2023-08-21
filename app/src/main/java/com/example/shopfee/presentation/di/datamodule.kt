package com.example.shopfee.presentation.di

import com.example.data.api.CoffeeService
import com.example.data.repoimpl.ApiRepoImpl
import com.example.domain.repo.ApiRepo
import com.example.utils.Consts.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val datamodule = module {
    factory<String> {
        BASE_URL
    }

    single<CoffeeService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CoffeeService::class.java)
    }
    single<ApiRepo> {
        ApiRepoImpl(service = get())
    }
}