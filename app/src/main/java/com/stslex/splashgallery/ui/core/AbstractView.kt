package com.stslex.splashgallery.ui.core

import com.stslex.splashgallery.ui.utils.SetImageWithGlide

interface AbstractView {

    fun show()
    fun hide()

    interface Text : AbstractView, com.stslex.core.TextMapper.Void
    interface Card : AbstractView {
        fun getCardAndSetTransitionName(transitionName: String): CustomCardView
        fun getCardView(): CustomCardView
    }

    interface Image : AbstractView {

        fun load(url: String, glide: SetImageWithGlide)
        fun getImage(): CustomImageView
        fun getImageAndSetTransitionName(transitionName: String): CustomImageView
    }
}