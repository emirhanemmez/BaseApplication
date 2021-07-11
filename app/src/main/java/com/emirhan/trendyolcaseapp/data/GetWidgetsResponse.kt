package com.emirhan.trendyolcaseapp.data

data class GetWidgetsResponse(
    val pagination: Pagination,
    val widgets: List<Widget>
)