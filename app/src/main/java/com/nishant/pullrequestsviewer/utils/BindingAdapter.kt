package com.nishant.pullrequestsviewer.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.nishant.pullrequestsviewer.R

@BindingAdapter("app:textIf")
fun setTextIf(textView: TextView, text: CharSequence?) {
    if (text.isNullOrBlank()) {
        textView.visibility = View.GONE
        return
    }

    textView.text = text
    textView.visibility = View.VISIBLE
}

@BindingAdapter("app:visibleIFNotNullOrBlank")
fun setVisibleIFNotNullOrBlank(textView: TextView, text: CharSequence?) {
    textView.isVisible = !text.isNullOrBlank()
}

@BindingAdapter("app:imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).error(R.drawable.ic_launcher_foreground).into(view)
}
