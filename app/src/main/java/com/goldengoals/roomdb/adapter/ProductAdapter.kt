package com.example.roomwithviewhw.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwithviewhw.entity.Product
import com.goldengoals.roomdb.R
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var products = emptyList<Product>()
    inner class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindProduct(product: Product) {
            itemView.txtID.text = product.id.toString()
            itemView.txtName.text = product.name.toString()
            itemView.txtPrice.text = product.price.toString()
            itemView.txtQuantity.text = product.qty.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindProduct(products[position])
    }

    fun setProductList(productList: List<Product>) {
        this.products = productList
        notifyDataSetChanged()
    }
}