package com.emirhan.trendyolcaseapp.data

data class Variant(
    val campaignId: Int,
    val listingId: String,
    val name: String,
    val price: Price,
    val value: String,
    val variantId: Int
)