package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.source.local

import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.db.AppDatabase
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.model.Product
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.model.asEntitiesModels
import javax.inject.Inject

class ProductLocalSource @Inject constructor(
    private val appDatabase: AppDatabase
) {

    suspend fun insertIntoLocal(products: List<Product>) {
        appDatabase.productDAO.inserts(products.asEntitiesModels())
    }
}
