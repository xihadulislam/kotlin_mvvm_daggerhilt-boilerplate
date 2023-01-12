package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.source.remote

import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.networks.response.ProductResponse
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.networks.service.ProductService
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils.ext.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRemoteSource @Inject constructor(
    private val productService: ProductService,
    private val interceptor: NetworkConnectionInterceptor
) : SafeApiRequest() {


    suspend fun getAllProducts(): ResResult<ProductResponse> =
        withContext(Dispatchers.IO) {
            try {
                if (!interceptor.isInternetAvailable()) {
                    throw NoInternetException("No network connection")
                }
                val response = apiRequest { productService.fetchAllProducts() }

                ResResult.Success(response)
            } catch (e: Exception) {
                if (e is RetroApiException) {
                    val code = e.getErrorCode()
                    ResResult.Error(e, code)
                } else {
                    ResResult.Error(e, -1)
                }
            }
        }


}