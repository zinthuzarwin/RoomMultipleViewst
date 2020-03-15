package com.example.roomwithviewhw.repository

import androidx.lifecycle.LiveData
import com.example.roomwithviewhw.dao.ProductDao
import com.example.roomwithviewhw.entity.Product

class ProductRepository (private val productDao: ProductDao){
    val getAllProduct: LiveData<List<Product>> = productDao.getAllProduct()

    suspend fun insertProduct(product:Product){
        productDao.insertProduct(product)
    }

    suspend fun deleteByID(id:Int){
        productDao.deleteByID(id)
    }

    suspend fun getByName(name: String): LiveData<List<Product>> {
        return productDao.getProductByName(name)
    }

    suspend fun getByPriceRange(minAmt: Double, maxAmt: Double): LiveData<List<Product>> {
        return productDao.getProductByPriceRange(minAmt,maxAmt)
    }

}