package com.example.roomwithviewhw.database

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomwithviewhw.dao.ProductDao
import com.example.roomwithviewhw.entity.Product

@Database(entities = arrayOf(Product::class),version = 1,exportSchema = false)
abstract class ProductDatabase : RoomDatabase(){
    abstract fun productDao(): ProductDao

    companion object{

        private var INSTANCE: ProductDatabase? = null

        fun getDatabase(
            context: Context
        ): ProductDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "product_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
