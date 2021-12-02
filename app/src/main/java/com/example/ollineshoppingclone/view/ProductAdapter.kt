package com.example.ollineshopping.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.ollineshopping.model.ProductModel

import com.example.ollineshopping.utils.Constatn
import com.example.ollineshoppingclone.R
import com.example.ollineshoppingclone.screen.ProductDetail.ProductDetailActivity
import kotlinx.android.synthetic.main.product_item_layout.view.*

class ProductAdapter(val items: List<ProductModel>) : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    class ProductHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val item = items[position]

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ProductDetailActivity::class.java)
            intent.putExtra(Constatn.EXTRA_DATA, item)
            it.context.startActivity(intent)
        }

        Glide.with(holder.itemView.context).load(Constatn.HOST_IMAGE + item.image)
            .into(holder.itemView.imgProduct)
        holder.itemView.tvName.text = item.name
        holder.itemView.tvPrice.text = item.price
    }

    override fun getItemCount(): Int {
        return items.count()
    }

}