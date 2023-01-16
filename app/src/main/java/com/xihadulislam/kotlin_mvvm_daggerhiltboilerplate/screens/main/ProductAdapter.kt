package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.R
import com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductAdapter @Inject constructor() :
    ListAdapter<Product, ProductAdapter.ViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))

        holder.itemView.setOnClickListener {

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var title: TextView = itemView.findViewById(R.id.tvTitle)
        private var price: TextView = itemView.findViewById(R.id.tvPrice)
        private var description: TextView = itemView.findViewById(R.id.tvDescription)
        private var image: ImageView = itemView.findViewById(R.id.imageView)

        fun bindView(product: Product) {
            title.text = product.title
            price.text = "$ ${product.price}"
            description.text = "${product.description}"

            Glide.with(image.context)
                .load(product.thumbnail)
                .into(image)
        }
    }
}

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}