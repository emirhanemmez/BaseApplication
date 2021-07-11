package com.emirhan.trendyolcaseapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDetailItem(
    val imageUrl: String,
    val title: String
) : Parcelable
