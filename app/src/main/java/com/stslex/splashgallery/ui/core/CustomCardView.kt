package com.stslex.splashgallery.ui.core

import android.content.Context
import android.util.AttributeSet
import android.view.View

class CustomCardView : com.google.android.material.card.MaterialCardView, AbstractView.Card {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun getCardAndSetTransitionName(transitionName: String): CustomCardView {
        this.transitionName = transitionName
        return this
    }

    override fun getCardView(): CustomCardView = this

    override fun show() {
        visibility = View.VISIBLE
    }

    override fun hide() {
        visibility = View.GONE
    }
}