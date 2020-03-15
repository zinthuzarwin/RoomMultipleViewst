package com.example.roomwithviewhw.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomwithviewhw.entity.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM product_table ORDER BY id ASC")
    fun getAllProduct(): LiveData<List<Product>>

    @Query("DELETE FROM product_table WHERE id = :id")
    suspend fun deleteByID(id: Int)

    @Query("SELECT * FROM product_table WHERE name = :name ORDER BY id ASC")
    fun getProductByName(name: String): LiveData<List<Product>>

    @Query("SELECT * FROM product_table WHERE price >= :minAmt AND price <= :maxAmt ORDER BY id ASC")
    fun getProductByPriceRange(minAmt: Double, maxAmt: Double): LiveData<List<Product>>
}