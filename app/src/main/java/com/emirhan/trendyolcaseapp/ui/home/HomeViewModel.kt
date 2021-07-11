package com.emirhan.trendyolcaseapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.emirhan.trendyolcaseapp.base.BaseViewModel
import com.emirhan.trendyolcaseapp.data.HomeAdapterItem
import com.emirhan.trendyolcaseapp.remote.repository.ProductRepository
import com.emirhan.trendyolcaseapp.remote.repository.WidgetRepository
import com.emirhan.trendyolcaseapp.utils.Result
import com.emirhan.trendyolcaseapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val widgetRepository: WidgetRepository,
    private val productRepository: ProductRepository
) : BaseViewModel() {

    private val homeAdapterItemList = ArrayList<HomeAdapterItem>()
    val homeAdapterItemListLiveData = MutableLiveData<Result<ArrayList<HomeAdapterItem>>>()

    fun getWidgets() = viewModelScope.launch {
        homeAdapterItemListLiveData.postValue(Result(Status.LOADING, null))
        homeAdapterItemList.clear()

        val response = widgetRepository.getWidgets()

        when (response.status) {
            Status.SUCCESS -> {
                response.data?.body()?.widgets?.forEach {
                    if (it.displayType != "SLIDER")
                        homeAdapterItemList.add(HomeAdapterItem(it.displayType, it))
                    else {
                        it.fullServiceUrl?.let { fullServiceUrl ->
                            getProducts(fullServiceUrl)
                        }
                    }
                }
            }
            Status.ERROR -> homeAdapterItemListLiveData.postValue(Result(Status.ERROR, null))
            else -> Unit
        }
    }

    private fun getProducts(fullServiceUrl: String) = viewModelScope.launch {
        val response = productRepository.getProducts(fullServiceUrl)
        when (response.status) {
            Status.SUCCESS -> {
                homeAdapterItemList.add(
                    HomeAdapterItem(
                        displayType = "SLIDER",
                        content = response.data?.body()
                    )
                )

                homeAdapterItemListLiveData.value = Result(Status.SUCCESS, homeAdapterItemList)
            }
            Status.ERROR -> homeAdapterItemListLiveData.postValue(Result(Status.ERROR, null))
            else -> Unit
        }

    }
}