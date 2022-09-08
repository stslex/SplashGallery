package com.stslex.core_ui

import android.widget.TextView
import androidx.core.view.isVisible

object TextUtils {

    fun TextView.map(data: String) {
        isVisible = true
        text = data.ifEmpty { resources.getString(R.string.string_unknown) }
    }
}