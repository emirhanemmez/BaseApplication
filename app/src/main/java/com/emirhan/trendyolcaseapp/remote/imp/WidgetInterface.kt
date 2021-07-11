package com.emirhan.trendyolcaseapp.remote.imp

import com.emirhan.trendyolcaseapp.data.GetWidgetsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WidgetInterface {
    @GET("display/personalized")
    suspend fun getWidgets(
        @Query("widgetPageName") widgetPageName: String = "interview",
        @Query("platform") platform: String = "android"
    ): Response<GetWidgetsResponse>
}