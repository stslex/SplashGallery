package com.stslex.splashgallery.utils

import androidx.recyclerview.widget.DiffUtil
import com.stslex.splashgallery.data.model.domain.image.ImageModel

class ImageDiffUtilCallback(
    private val oldList: List<ImageModel>,
    private val newList: List<ImageModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition].id == oldList[oldItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition] == oldList[oldItemPosition]

}