package com.emirhan.trendyolcaseapp.base

import com.emirhan.trendyolcaseapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Result<T> {
        return withContext(Dispatchers.IO) {
            Result.loading()
            try {
                Result.success(apiCall.invoke())
            } catch (throwable: Throwable) {
                Result.error("TODO")
            }
        }
    }
}