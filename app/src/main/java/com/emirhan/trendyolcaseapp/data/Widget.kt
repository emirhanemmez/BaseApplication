package com.emirhan.trendyolcaseapp.data

data class Widget(
    val bannerContents: List<BannerContent>,
    val displayCount: Int,
    val displayOptions: DisplayOptions,
    val endDate: String,
    val eventKey: String,
    val fullServiceUrl: String?,
    val fullServiceUrlWithPage: String?,
    val height: Int,
    val id: Int,
    val marketing: MarketingX,
    val refreshRequired: Boolean,
    val serviceUrl: String?,
    val startDate: String,
    val title: String,
    val type: String,
    val widgetNavigation: WidgetNavigation,
    val width: Int,
    val displayType: String?,
)