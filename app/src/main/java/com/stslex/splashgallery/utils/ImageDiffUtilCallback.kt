package com.stslex.splashgallery.utils

import androidx.recyclerview.widget.DiffUtil
import com.stslex.splashgallery.ui.photos.PhotosUI

class ImageDiffUtilCallback(
    private val oldList: List<PhotosUI>,
    private val newList: List<PhotosUI>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].same(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition] == oldList[oldItemPosition]

}