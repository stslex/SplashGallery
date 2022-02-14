package com.stslex.splashgallery.ui.core

import android.view.View

interface OnClickListener {
    fun clickImage(view: View, url: String)
    fun clickUser(view: View)
}