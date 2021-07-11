package com.emirhan.trendyolcaseapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.emirhan.trendyolcaseapp.R
import com.emirhan.trendyolcaseapp.data.Product
import com.emirhan.trendyolcaseapp.data.ProductDetailItem
import com.emirhan.trendyolcaseapp.databinding.ListItemProductSliderBinding

class ProductSliderAdapter(
    private val list: List<Product>,
    private val sliderAdapterItemClickListener: (ProductDetailItem) -> Unit
) : RecyclerView.Adapter<ProductSliderAdapter.ViewHolder>() {

    private lateinit var binding: ListItemProductSliderBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_product_slider,
            parent,
            false
        )
        return ViewHolder(binding, sliderAdapterItemClickListener, list)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(
        private val binding: ListItemProductSliderBinding,
        private val sliderAdapterItemClickListener: (ProductDetailItem) -> Unit,
        list: List<Product>
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val item = list[adapterPosition]
                sliderAdapterItemClickListener.invoke(
                    ProductDetailItem(
                        imageUrl = item.imageUrl,
                        title = item.name
                    )
                )
            }
        }

        fun bindData(product: Product) {
            binding.product = product
        }
    }
}