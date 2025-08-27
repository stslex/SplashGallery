package com.stslex.splashgallery.core_ui

import android.view.View

interface OnClickListener {
    fun clickImage(view: View, url: String)
    fun clickUser(view: View)
}