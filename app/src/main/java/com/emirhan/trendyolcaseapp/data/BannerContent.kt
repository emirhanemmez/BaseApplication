package com.emirhan.trendyolcaseapp.data

data class BannerContent(
    val bannerEventKey: String,
    val displayOrder: Int,
    val imageUrl: String,
    val marketing: Marketing,
    val navigation: Navigation,
    val subtitle: String,
    val title: String
)