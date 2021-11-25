package com.stslex.splashgallery.ui.core

import android.view.View
import com.google.android.material.card.MaterialCardView

interface OnClickListener {
    fun clickImage(view: View, url: String)
    fun clickUser(view: MaterialCardView)
}