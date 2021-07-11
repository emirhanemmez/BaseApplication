package com.emirhan.trendyolcaseapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.emirhan.trendyolcaseapp.R
import com.emirhan.trendyolcaseapp.data.HomeAdapterItem
import com.emirhan.trendyolcaseapp.data.Product
import com.emirhan.trendyolcaseapp.data.ProductDetailItem
import com.emirhan.trendyolcaseapp.data.Widget
import com.emirhan.trendyolcaseapp.databinding.ItemDefaultBinding
import com.emirhan.trendyolcaseapp.databinding.ItemHomeAdapterBinding
import javax.inject.Inject

class HomeAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemList = ArrayList<HomeAdapterItem>()

    lateinit var onHomeItemClickListener: (ProductDetailItem) -> Unit

    private lateinit var itemSingleBannerBinding: ItemHomeAdapterBinding
    private lateinit var itemProductSliderBinding: ItemHomeAdapterBinding
    private lateinit var itemDefaultBinding: ItemDefaultBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ITEM_VIEW_TYPE_SINGLE_BANNER -> {
                itemSingleBannerBinding = DataBindingUtil.inflate(
                    layoutInflater,
                    R.layout.item_home_adapter,
                    parent,
                    false
                )
                SingleBannerViewHolder(itemSingleBannerBinding, onHomeItemClickListener)
            }
            ITEM_VIEW_TYPE_PRODUCT_SLIDER -> {
                itemProductSliderBinding = DataBindingUtil.inflate(
                    layoutInflater,
                    R.layout.item_home_adapter,
                    parent,
                    false
                )
                ProductSliderViewHolder(itemProductSliderBinding, onHomeItemClickListener)
            }
            else -> {
                itemDefaultBinding = DataBindingUtil.inflate(
                    layoutInflater,
                    R.layout.item_default,
                    parent,
                    false
                )
                DefaultViewHolder(itemDefaultBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = this.itemList[position]

        when (getItemViewType(position)) {
            ITEM_VIEW_TYPE_SINGLE_BANNER -> (holder as SingleBannerViewHolder).bindData(item)
            ITEM_VIEW_TYPE_PRODUCT_SLIDER -> (holder as ProductSliderViewHolder).bindData(item)
            ITEM_VIEW_TYPE_DEFAULT -> Unit
        }
    }

    override fun getItemCount(): Int = this.itemList.size

    override fun getItemViewType(position: Int): Int {
        return when (this.itemList[position].displayType) {
            "SINGLE" -> ITEM_VIEW_TYPE_SINGLE_BANNER
            "SLIDER" -> ITEM_VIEW_TYPE_PRODUCT_SLIDER
            else -> ITEM_VIEW_TYPE_DEFAULT
        }
    }

    fun setHomeAdapterItems(list: ArrayList<HomeAdapterItem>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    class SingleBannerViewHolder(
        private val binding: ItemHomeAdapterBinding,
        private val onHomeAdapterItemClickListener: (ProductDetailItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: HomeAdapterItem) {
            if (item.content is Widget) {
                val singleBannerAdapter =
                    SingleBannerAdapter(item.content, onHomeAdapterItemClickListener)
                binding.viewPager.adapter = singleBannerAdapter
            }
        }
    }

    class ProductSliderViewHolder(
        private val binding: ItemHomeAdapterBinding,
        private val onHomeAdapterItemClickListener: (ProductDetailItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: HomeAdapterItem) {
            if (item.content is ArrayList<*>) {
                val productSliderAdapter = ProductSliderAdapter(
                    item.content as ArrayList<Product>,
                    onHomeAdapterItemClickListener
                )
                binding.viewPager.adapter = productSliderAdapter
            }
        }
    }

    class DefaultViewHolder(
        binding: ItemDefaultBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val ITEM_VIEW_TYPE_SINGLE_BANNER = 0
        private const val ITEM_VIEW_TYPE_PRODUCT_SLIDER = 1
        private const val ITEM_VIEW_TYPE_DEFAULT = 2
    }
}