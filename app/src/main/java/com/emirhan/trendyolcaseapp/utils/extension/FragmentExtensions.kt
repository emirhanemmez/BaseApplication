package com.emirhan.trendyolcaseapp.utils.extension

import androidx.fragment.app.Fragment

fun Fragment.handleApiError(
    retry: (() -> Unit)? = null
) {
    requireView().showSnackBar("There is Error, Please try again!", retry)
}