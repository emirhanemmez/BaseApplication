package com.emirhan.trendyolcaseapp.di

import android.app.Dialog
import android.content.Context
import com.emirhan.trendyolcaseapp.R
import com.emirhan.trendyolcaseapp.ui.home.adapter.HomeAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    @ActivityScoped
    fun provideProgressBar(@ActivityContext context: Context) = Dialog(context).apply {
        setContentView(R.layout.dialog_progress)
    }

    @Provides
    @ActivityScoped
    fun provideHomeAdapter() = HomeAdapter()
}
