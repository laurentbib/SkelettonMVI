package com.biblublab.skelettonmvi.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun ImageView.loadImg(url : String, errorDrawable : Int){
    Glide.with(context)
        .load(url)
        .placeholder(errorDrawable)
        .error(errorDrawable)
        .into(this)
}

fun ShimmerFrameLayout.beginShimmerAnim() {
    if (!isShimmerStarted) startShimmer()
    this.show()
}

fun ShimmerFrameLayout.endShimmerAnim() {
    this.hide()
    if (isShimmerStarted) stopShimmer()
}

fun View.show() {
    animate().alpha(1.0f)
}

fun View.hide() {
    animate().alpha(0.0f)
}

fun View.visible(isVisible : Boolean){
    if(isVisible) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}