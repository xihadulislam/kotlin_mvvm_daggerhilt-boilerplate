package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.networks.response

import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.model.Product

data class ProductResponse(
    val products: List<Product>,
    val total: Long,
    val skip: Long,
    val limit: Long
)