package com.example.shopfee.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Coffee
import com.example.domain.model.CoffeeResponse
import com.example.domain.usecases.GetAllCoffeeUseCase
import com.example.domain.usecases.GetProductsByCategoryUseCase
import com.example.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class CategoryViewModel(val getAllCoffeeUseCase: GetAllCoffeeUseCase
, val getProductsByCategoryUseCase: GetProductsByCategoryUseCase): ViewModel() {


    val stateOfResponseAllCoffee = MutableStateFlow<Resource<List<Coffee>>>(Resource.Loading())
    fun getAllCoffee() = viewModelScope.launch {
        stateOfResponseAllCoffee.value = Resource.Loading()
        val response = getAllCoffeeUseCase.getAllCoffee()
        stateOfResponseAllCoffee.value = handleAllCoffeeResponse(response)

    }





    private fun handleAllCoffeeResponse(response: Response<CoffeeResponse>): Resource<List<Coffee>>{
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







    val stateOfResponseProductsByCategory = MutableStateFlow<Resource<List<Coffee>>>(Resource.Loading())

    fun getProductsByCategory(category: String) = viewModelScope.launch {
        stateOfResponseProductsByCategory.value = Resource.Loading()
        val response = getProductsByCategoryUseCase.getProductByCategory(category)
        stateOfResponseProductsByCategory.value = handleResponseProductsByCategory(response)

    }



    private fun handleResponseProductsByCategory(response: Response<CoffeeResponse>): Resource<List<Coffee>>{
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




    val stateOfResponseProductsByCategoryNonCoffee = MutableStateFlow<Resource<List<Coffee>>>(Resource.Loading())

    fun getProductsByCategoryNonCoffee(category: String) = viewModelScope.launch {
        stateOfResponseProductsByCategoryNonCoffee.value = Resource.Loading()
        val response = getProductsByCategoryUseCase.getProductByCategory(category)
        stateOfResponseProductsByCategoryNonCoffee.value = handleResponseProductsByCategoryNonCoffee(response)

    }



    private fun handleResponseProductsByCategoryNonCoffee(response: Response<CoffeeResponse>): Resource<List<Coffee>>{
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