package com.stslex.splashgallery.ui.core

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.bumptech.glide.Glide
import com.stslex.splashgallery.R

class CustomImageView : androidx.appcompat.widget.AppCompatImageView, AbstractView.Image {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    @SuppressLint("CheckResult")
    override fun load(url: String, needCrop: Boolean, needCircle: Boolean) {
        val urlSet = if (url.isEmpty()) {
            R.drawable.ic_launcher_new_foreground
        } else url
        this.transitionName = url
        val glide = Glide.with(this).load(urlSet)
        if (needCrop) glide.centerCrop()
        if (needCircle) glide.circleCrop()
        glide.into(this)
    }

    override fun getImage(): CustomImageView = this

    override fun show() {
        visibility = View.VISIBLE
    }

    override fun hide() {
        visibility = View.GONE
    }
}