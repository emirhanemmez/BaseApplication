package com.emirhan.trendyolcaseapp.utils.extension

import android.app.Dialog
import android.view.View
import com.emirhan.trendyolcaseapp.utils.Status
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String, action: (() -> Unit)? = null) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    action?.let {
        snackBar.setAction("Retry") {
            it()
        }
    }
    snackBar.show()
}

fun Dialog.showProgressDialog(status: Status) {
    when (status) {
        Status.LOADING -> this.show()
        Status.SUCCESS -> this.dismiss()
        Status.ERROR -> this.dismiss()
    }
}