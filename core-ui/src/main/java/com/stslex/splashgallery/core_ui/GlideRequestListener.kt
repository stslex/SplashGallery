package com.stslex.splashgallery.core_ui

import android.graphics.drawable.Drawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

@JvmInline
value class GlideRequestListener(
    private val transition: () -> Unit
) : RequestListener<Drawable> {

    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable?>,
        isFirstResource: Boolean
    ): Boolean {
        transition()
        return true
    }

    override fun onResourceReady(
        resource: Drawable,
        model: Any,
        target: Target<Drawable?>?,
        dataSource: DataSource,
        isFirstResource: Boolean
    ): Boolean {
        transition()
        return false
    }
}