package com.emirhan.trendyolcaseapp.di

import com.emirhan.trendyolcaseapp.BuildConfig
import com.emirhan.trendyolcaseapp.remote.imp.ProductInterface
import com.emirhan.trendyolcaseapp.remote.imp.WidgetInterface
import com.emirhan.trendyolcaseapp.remote.repository.ProductRepository
import com.emirhan.trendyolcaseapp.remote.repository.WidgetRepository
import com.emirhan.trendyolcaseapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideOkHttpClient() =
        if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .addInterceptor(logger)
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(100, TimeUnit.SECONDS)
                .addInterceptor {
                    val original = it.request()

                    val request = original.newBuilder()
                        .header("Build", "512")
                        .header("Platform", "android")
                        .header("Content-Type", "application/json")
                        .method(original.method, original.body)
                        .build()

                    it.proceed(request)
                }
                .build()
        } else {
            OkHttpClient.Builder()
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(100, TimeUnit.SECONDS)
                .build()
        }

    @Provides
    fun provideWidgetInterface(okHttpClient: OkHttpClient): WidgetInterface = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WidgetInterface::class.java)

    @Provides
    fun provideProductInterface(okHttpClient: OkHttpClient): ProductInterface = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductInterface::class.java)

    @Provides
    fun provideWidgetRepository(movieInterface: WidgetInterface) = WidgetRepository(movieInterface)

    @Provides
    fun provideProductRepository(productInterface: ProductInterface) =
        ProductRepository(productInterface)
}