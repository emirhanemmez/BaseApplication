package com.emirhan.trendyolcaseapp.remote.repository

import com.emirhan.trendyolcaseapp.base.BaseRepository
import com.emirhan.trendyolcaseapp.remote.imp.WidgetInterface

open class WidgetRepository(
    private val repositoryInterface: WidgetInterface
) : BaseRepository() {

    suspend fun getWidgets() = safeApiCall {
        repositoryInterface.getWidgets()
    }
}