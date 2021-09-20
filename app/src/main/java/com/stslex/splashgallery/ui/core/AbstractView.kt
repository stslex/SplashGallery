package com.stslex.splashgallery.ui.core

import com.stslex.splashgallery.core.TextMapper

interface AbstractView {

    fun show()
    fun hide()

    interface Text : AbstractView, TextMapper.Void
    interface Card : AbstractView {
        fun transit(transitionName: String)
        fun getCard(): CustomCardView
    }

    interface Image : AbstractView {

        fun load(url: String, needCrop: Boolean = false, needCircle: Boolean = false)
        fun getImage(): CustomImageView
    }
}