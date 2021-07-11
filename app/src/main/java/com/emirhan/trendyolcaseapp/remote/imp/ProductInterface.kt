package com.emirhan.trendyolcaseapp.remote.imp

import com.emirhan.trendyolcaseapp.data.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ProductInterface {
    @GET
    suspend fun getProducts(@Url fullServiceUrl: String): Response<List<Product>>
}