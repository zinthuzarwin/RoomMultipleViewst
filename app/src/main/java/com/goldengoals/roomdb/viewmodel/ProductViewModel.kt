package com.example.roomwithviewhw.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomwithviewhw.database.ProductDatabase
import com.example.roomwithviewhw.entity.Product
import com.example.roomwithviewhw.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application){

    private val repository: ProductRepository

    val getAllProduct: LiveData<List<Product>>
    lateinit var getByNameList: LiveData<List<Product>>
    lateinit var getByPriceList: LiveData<List<Product>>

    init {
        val productDao = ProductDatabase.getDatabase(application).productDao()
        repository= ProductRepository(productDao)
        getAllProduct=repository.getAllProduct
    }

    fun insertAll(product: Product) = viewModelScope.launch {
        repository.insertProduct(product)
    }

    fun deleteByID(id :Int) = viewModelScope.launch {
        repository.deleteByID(id)
    }

    fun getByName(name:String) = viewModelScope.launch {
        getByNameList=repository.getByName(name)
    }

    fun getByPrice(minAmt:Double,maxAmt:Double) = viewModelScope.launch {
        getByPriceList=repository.getByPriceRange(minAmt,maxAmt)
    }
}