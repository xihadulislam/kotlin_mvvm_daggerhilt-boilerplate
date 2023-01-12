package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.db.dao

import androidx.room.Dao
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.data.db.entities.ProductEntities

@Dao
interface ProductDAO : BaseDAO<ProductEntities> {


}