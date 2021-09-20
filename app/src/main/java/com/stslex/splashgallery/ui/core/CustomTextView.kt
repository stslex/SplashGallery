package com.stslex.splashgallery.ui.core

import android.content.Context
import android.util.AttributeSet
import android.view.View

class CustomTextView : androidx.appcompat.widget.AppCompatTextView, AbstractView.Text {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun map(data: String) {
        show()
        text = data
    }

    override fun show() {
        visibility = View.VISIBLE
    }

    override fun hide() {
        visibility = View.GONE
    }
}