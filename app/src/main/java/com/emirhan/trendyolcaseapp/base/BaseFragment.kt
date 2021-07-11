package com.emirhan.trendyolcaseapp.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.emirhan.trendyolcaseapp.ui.MainActivity

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment() {

    protected lateinit var binding: VB

    protected abstract val viewModel: VM

    protected val progressBar: Dialog by lazy {
        (requireActivity() as MainActivity).progressDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onBindView()
    }

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    abstract fun onBindView()
}