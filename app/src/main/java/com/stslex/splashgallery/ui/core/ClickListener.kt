package com.stslex.splashgallery.ui.core

import android.view.View

interface ClickListener {
    fun clickImage(view: View, url: String)
    fun clickUser(view: View)
}