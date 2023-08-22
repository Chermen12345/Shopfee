package com.example.shopfee.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Coffee
import com.example.domain.model.CoffeeResponse
import com.example.domain.usecases.SearchProductUseCase
import com.example.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class AppViewModel(val searchProductUseCase: SearchProductUseCase): ViewModel() {
    val stateOfResponseSearch = MutableStateFlow<Resource<List<Coffee>>>(Resource.Loading())
    fun searchProduct(q: String) = viewModelScope.launch {
        stateOfResponseSearch.value = Resource.Loading()
        val response = searchProductUseCase.searchProduct(q)
        stateOfResponseSearch.value = handleAllCoffeeResponse(response)

    }





    private fun handleAllCoffeeResponse(response: Response<CoffeeResponse>): Resource<List<Coffee>> {
        try {
            if (response.isSuccessful){

                response.body()?.let {body->

                    val listOfCoffee = body.coffees

                    return Resource.Success(listOfCoffee)
                }


            } else{
                return Resource.Error(message = response.message())
            }

        }catch (e: HttpException){
            return Resource.Error(message = response.message())
        }
        return Resource.Error(response.message())
    }
}