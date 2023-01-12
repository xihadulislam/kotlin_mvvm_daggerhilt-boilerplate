package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.model.Product

@Entity
data class ProductEntities constructor(
    @PrimaryKey
    val id: Long,
    val price: Long,
    val rating: Double,
    val data: String,
)

fun List<ProductEntities>.asDomainModels(): List<Product> {
    return map {
        Gson().fromJson(it.data, Product::class.java)
    }
}

fun ProductEntities.asDomainModel(): Product {
    return Gson().fromJson(this.data, Product::class.java)
}