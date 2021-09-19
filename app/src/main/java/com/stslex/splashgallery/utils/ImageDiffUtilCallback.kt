package com.stslex.splashgallery.utils

import androidx.recyclerview.widget.DiffUtil
import com.stslex.splashgallery.ui.photos.PhotosUI

class ImageDiffUtilCallback(
    private val oldList: List<PhotosUI.Base>,
    private val newList: List<PhotosUI.Base>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition].imageId == oldList[oldItemPosition].imageId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition] == oldList[oldItemPosition]

}