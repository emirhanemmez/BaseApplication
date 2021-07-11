package com.emirhan.trendyolcaseapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.emirhan.trendyolcaseapp.data.HomeAdapterItem
import com.emirhan.trendyolcaseapp.remote.repository.ProductRepository
import com.emirhan.trendyolcaseapp.remote.repository.WidgetRepository
import com.emirhan.trendyolcaseapp.utils.Result
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ObsoleteCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var widgetRepository: WidgetRepository

    @Mock
    private lateinit var productRepository: ProductRepository

    private lateinit var viewModel: HomeViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ExperimentalCoroutinesApi
    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = HomeViewModel(widgetRepository, productRepository)
    }

    @Test
    fun `get widget list`() {
        viewModel.viewModelScope.launch {
            val observer =
                Mockito.mock(Observer::class.java) as Observer<Result<ArrayList<HomeAdapterItem>>>
            viewModel.homeAdapterItemListLiveData.observeForever(observer)
            viewModel.getWidgets()
            assertNotNull(viewModel.homeAdapterItemListLiveData.value?.data)
        }
    }

    @ExperimentalCoroutinesApi
    @After
    fun finish() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

}