package com.bignerdranch.android.movieapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("load")
fun loadImage(view: ImageView, url: String?) {

    url?.let {
        Glide.with(view).load(it).into(view)
    }


}