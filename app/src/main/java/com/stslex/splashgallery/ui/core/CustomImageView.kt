package com.stslex.splashgallery.ui.core

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.stslex.core_ui.SetImageWithGlide

class CustomImageView : androidx.appcompat.widget.AppCompatImageView, AbstractView.Image {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    @SuppressLint("CheckResult")
    override fun load(url: String, glide: SetImageWithGlide) {
    }

    override fun getImage(): CustomImageView = this

    override fun getImageAndSetTransitionName(transitionName: String): CustomImageView =
        this.apply {
            this.transitionName = transitionName
        }

    override fun show() {
        visibility = View.VISIBLE
    }

    override fun hide() {
        visibility = View.GONE
    }
}