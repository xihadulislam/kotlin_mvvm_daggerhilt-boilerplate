package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.preference.AppSharedPref
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.repositories.ProductRepo
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.model.Product
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils.ext.Event
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils.ext.ResResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appSharedPref: AppSharedPref,
    private val productRepo: ProductRepo
) : ViewModel() {
    companion object {
        private const val TAG = "MainViewModel"
    }

    private val _apiRequestSuccess = MutableLiveData<Event<List<Product>>>()
    val apiRequestSuccess: LiveData<Event<List<Product>>> = _apiRequestSuccess

    private val _apiRequestFailed = MutableLiveData<Event<String?>>()
    val apiRequestFailed: LiveData<Event<String?>> = _apiRequestFailed

    fun fetchAllProductsFromSever() {
        viewModelScope.launch(Dispatchers.IO) {
            productRepo.getAllProducts().let { result ->
                if (result is ResResult.Success) {
                    _apiRequestSuccess.postValue(Event(result.data.products))
                    insertIntoLocal(result.data.products)
                } else {
                    val errorMessage = (result as ResResult.Error).exception.message
                    _apiRequestFailed.postValue(Event(errorMessage))
                }
            }

        }
    }

    private fun insertIntoLocal(products: List<Product>) {
        viewModelScope.launch {
            productRepo.insertIntoLocal(products)
        }
    }


}
