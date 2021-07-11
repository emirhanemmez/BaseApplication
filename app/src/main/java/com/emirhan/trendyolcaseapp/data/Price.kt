package com.emirhan.trendyolcaseapp.data

data class Price(
    val discountedPriceInfo: String,
    val mOriginalPrice: Int,
    val marketPrice: Double,
    val salePrice: Double
)