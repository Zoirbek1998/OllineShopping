package com.example.ollineshopping.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ollineshopping.model.CategoryModel
import com.example.ollineshopping.utils.Constatn
import com.example.ollineshoppingclone.R
import kotlinx.android.synthetic.main.categoriy_item_layout.view.*

// CategoryAdapterCallBack bu yerda item bosiliwi
interface CategoryAdapterCallBack{
    fun onClikItem(item:CategoryModel)
}

class CategoryAdapter(val items: List<CategoryModel>, val callback: CategoryAdapterCallBack) : RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {

    class ItemHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
     return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.categoriy_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]

        holder.itemView.setOnClickListener {
            items.forEach {
                it.chacked = false
            }

            callback.onClikItem(item)
            item.chacked =true

            notifyDataSetChanged()
        }

        holder.itemView.tvTitle.text = item.title
        Glide.with(holder.itemView.context).load(Constatn.HOST_IMAGE+item.icon).into(holder.itemView.imgCat)
            if (item.chacked){
                holder.itemView.cardview.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.colorPrimary))
                holder.itemView.tvTitle.setTextColor(Color.WHITE)
            }
        else{
                holder.itemView.cardview.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.gray))
                holder.itemView.tvTitle.setTextColor(Color.BLACK)
            }

    }

    override fun getItemCount(): Int {
        return items.count()
    }

}