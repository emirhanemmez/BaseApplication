package com.emirhan.trendyolcaseapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.emirhan.trendyolcaseapp.base.BaseFragment
import com.emirhan.trendyolcaseapp.databinding.FragmentHomeBinding
import com.emirhan.trendyolcaseapp.ui.home.adapter.HomeAdapter
import com.emirhan.trendyolcaseapp.utils.Status
import com.emirhan.trendyolcaseapp.utils.extension.handleApiError
import com.emirhan.trendyolcaseapp.utils.extension.showProgressDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    @Inject
    lateinit var homeAdapter: HomeAdapter

    override val viewModel: HomeViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onBindView() {
        homeAdapter.onHomeItemClickListener = {
            findNavController().navigate(
                HomeFragmentDirections.actionMovieFragmentToDetailsFragment(
                    it
                )
            )
        }

        binding.recyclerViewHome.adapter = homeAdapter
        viewModel.getWidgets()

        viewModel.homeAdapterItemListLiveData.observe(viewLifecycleOwner) { result ->
            progressBar.showProgressDialog(result.status)

            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.let { homeAdapterItems ->
                        homeAdapter.setHomeAdapterItems(homeAdapterItems)
                    }
                }
                Status.LOADING -> Unit
                Status.ERROR -> handleApiError()
            }
        }
    }
}