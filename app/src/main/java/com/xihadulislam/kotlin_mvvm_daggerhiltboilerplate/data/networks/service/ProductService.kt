package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.networks.service

import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.networks.response.ProductResponse
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils.EndPoints
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {

    @GET(EndPoints.FETCH_PRODUCTS_API)
    suspend fun fetchAllProducts(): Response<ProductResponse>


}