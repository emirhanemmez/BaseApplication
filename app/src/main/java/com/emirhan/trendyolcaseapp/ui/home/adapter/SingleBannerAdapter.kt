package com.emirhan.trendyolcaseapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.emirhan.trendyolcaseapp.R
import com.emirhan.trendyolcaseapp.data.BannerContent
import com.emirhan.trendyolcaseapp.data.ProductDetailItem
import com.emirhan.trendyolcaseapp.data.Widget
import com.emirhan.trendyolcaseapp.databinding.ListItemSingleBannerBinding

class SingleBannerAdapter(
    private val widget: Widget,
    private val bannerAdapterItemClickListener: (ProductDetailItem) -> Unit
) : RecyclerView.Adapter<SingleBannerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_single_banner,
                parent,
                false
            ),
            bannerAdapterItemClickListener,
            widget
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(widget.width, widget.height, widget.bannerContents[position])
    }

    override fun getItemCount(): Int = widget.bannerContents.size

    class ViewHolder(
        private val binding: ListItemSingleBannerBinding,
        private val bannerAdapterItemClickListener: (ProductDetailItem) -> Unit,
        widget: Widget
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val item = widget.bannerContents[adapterPosition]
                bannerAdapterItemClickListener.invoke(
                    ProductDetailItem(
                        imageUrl = item.imageUrl,
                        title = item.title
                    )
                )
            }
        }

        fun bindData(width: Int, height: Int, bannerContent: BannerContent) {
            binding.bannerWidth = width
            binding.bannerHeight = height
            binding.bannerContent = bannerContent
        }
    }
}