package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.model

import com.google.gson.Gson
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.db.entities.ProductEntities


data class Product(
    val id: Long,
    val title: String,
    val description: String,
    val price: Long,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Long,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
) {
    public fun toJson(): String = Gson().toJson(this)

    companion object {
        public fun fromJson(json: String): Product = Gson().fromJson(json, Product::class.java)
    }
}

fun List<Product>.asEntitiesModels(): List<ProductEntities> {
    return map {
        ProductEntities(id = it.id, price = it.price, rating = it.rating, data = it.toJson())
    }
}

fun Product.asEntitiesModel(): ProductEntities {
    return ProductEntities(
        id = this.id,
        price = this.price,
        rating = this.rating,
        data = this.toJson()
    )
}
