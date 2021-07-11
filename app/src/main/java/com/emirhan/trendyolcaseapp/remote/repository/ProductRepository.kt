package com.emirhan.trendyolcaseapp.remote.repository

import com.emirhan.trendyolcaseapp.base.BaseRepository
import com.emirhan.trendyolcaseapp.remote.imp.ProductInterface

open class ProductRepository(
    private val productInterface: ProductInterface
) : BaseRepository() {
    suspend fun getProducts(fullServiceUrl: String) = safeApiCall {
        productInterface.getProducts(fullServiceUrl)
    }
}