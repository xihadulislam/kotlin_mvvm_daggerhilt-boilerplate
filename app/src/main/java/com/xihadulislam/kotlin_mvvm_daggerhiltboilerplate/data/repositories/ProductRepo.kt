package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.repositories

import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.source.local.ProductLocalSource
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.source.remote.ProductRemoteSource
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.model.Product
import javax.inject.Inject

class ProductRepo @Inject constructor(
    private val productRemoteSource: ProductRemoteSource,
    private val productLocalSource: ProductLocalSource
) {

    suspend fun getAllProducts() = productRemoteSource.getAllProducts()

    suspend fun insertIntoLocal(products: List<Product>) =
        productLocalSource.insertIntoLocal(products)


}