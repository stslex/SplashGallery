package com.stslex.splashgallery.ui.utils

import android.widget.TextView
import androidx.core.view.isVisible
import com.stslex.splashgallery.R

object TextUtils {

    fun TextView.map(data: String) {
        isVisible = true
        text = data.ifEmpty { resources.getString(R.string.string_unknown) }
    }
}