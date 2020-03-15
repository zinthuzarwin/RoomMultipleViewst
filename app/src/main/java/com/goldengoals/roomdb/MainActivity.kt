package com.goldengoals.roomdb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomwithviewhw.adapter.ProductAdapter
import com.example.roomwithviewhw.entity.Product
import com.example.roomwithviewhw.viewmodel.ProductViewModel

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel
    private val addActivityCode = 1
    private val deleteByIDActivityCode = 2
    private val getProcuctByNameActivityCode = 3
    private val getProductByPriceRangeActivityCode = 4
    private val productAdapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerProduct.adapter=productAdapter
        recyclerProduct.layoutManager=LinearLayoutManager(this)
        productViewModel=ViewModelProvider(this).get(ProductViewModel::class.java)

        btnAdd.setOnClickListener {
            startActivityForResult(Intent(this@MainActivity,AddActivity::class.java),addActivityCode)
        }

        btnGetAll.setOnClickListener {
            productViewModel.getAllProduct.observe(
                this, Observer { productList -> productList.let {
                    productAdapter.setProductList(it)
                } }
            )
        }

        btnDelById.setOnClickListener {
            startActivityForResult(Intent(this@MainActivity,DeleteByIdActivity::class.java),deleteByIDActivityCode)
        }

        btnGetProductByName.setOnClickListener {
            startActivityForResult(Intent(this@MainActivity,GetByNameActivity::class.java),getProcuctByNameActivityCode)
        }

        btnGetProductByPrice.setOnClickListener {
            startActivityForResult(Intent(this@MainActivity,GetByPriceRangeActivity::class.java),getProductByPriceRangeActivityCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == addActivityCode && resultCode == Activity.RESULT_OK){
            var productArray = data?.getStringArrayExtra(AddActivity.EXTRA_REPLY)
            var product = Product()
            product.id = productArray?.get(0)?.toInt()
            product.name = productArray?.get(1)?.toString()
            product.price = productArray?.get(2)?.toDouble()
            product.qty = productArray?.get(3)?.toInt()
            productViewModel.insertAll(product)

            productViewModel.getAllProduct.observe(
                this, Observer { productList -> productList.let {
                    productAdapter.setProductList(it)
                } }
            )
        }

        if (requestCode == deleteByIDActivityCode && resultCode == Activity.RESULT_OK){

            var deleteId= data?.getStringExtra(DeleteByIdActivity.EXTRA_REPLY)
            var idInt = deleteId.toString().toInt()
            productViewModel.deleteByID(idInt)
            productViewModel.getAllProduct.observe(
                this, Observer { productList -> productList.let {
                    productAdapter.setProductList(it)
                } }
            )
        }

        if (requestCode == getProcuctByNameActivityCode && resultCode == Activity.RESULT_OK){

            var getName= data?.getStringExtra(GetByNameActivity.EXTRA_REPLY)
            var name = getName.toString()
            productViewModel.getByName(name)
            productViewModel.getByNameList.observe(
                this, Observer { productList -> productList.let {
                    productAdapter.setProductList(it)
                } }
            )
        }

        if (requestCode == getProductByPriceRangeActivityCode && resultCode == Activity.RESULT_OK){

            var priceArray = data?.getStringArrayExtra(GetByPriceRangeActivity.EXTRA_REPLY)
            Log.e("Error",".... ${Arrays.toString(priceArray)} ....")
            var minAmt = priceArray!![0].toString().toDouble()
            var maxAmt = priceArray!![1].toString().toDouble()
            productViewModel.getByPrice(minAmt,maxAmt)
            productViewModel.getByPriceList.observe(
                this, Observer { productList -> productList.let {
                    productAdapter.setProductList(it)
                } }
            )
        }


    }
}
