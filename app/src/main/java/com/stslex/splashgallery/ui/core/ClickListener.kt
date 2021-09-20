package com.stslex.splashgallery.ui.core

interface ClickListener<T> {
    fun clickImage(item: T)
    fun clickUser(item: T)
}