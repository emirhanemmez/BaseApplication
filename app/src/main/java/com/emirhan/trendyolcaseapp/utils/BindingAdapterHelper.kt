package com.emirhan.trendyolcaseapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

private var requestOptions: RequestOptions? = null
private var aspectHeight = 0
private var aspectWidth = 0

@BindingAdapter(value = ["url", "width", "height"], requireAll = false)
fun loadImage(view: ImageView, url: String?, width: Int?, height: Int?) {
    if (requestOptions == null)
        requestOptions = RequestOptions()

    aspectWidth = view.width
    aspectHeight =
        if (width != null && height != null)
            view.width * height / width
        else view.height

    Glide.with(view)
        .load(url)
        .apply(requestOptions!!.override(aspectWidth, aspectHeight))
        .into(view)
}
