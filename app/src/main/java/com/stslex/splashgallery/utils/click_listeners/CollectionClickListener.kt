package com.stslex.splashgallery.utils.click_listeners

import android.widget.ImageView
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel

class CollectionClickListener(val clickListener: (CollectionModel, ImageView) -> Unit) {
    fun onClick(
        collectionModel: CollectionModel,
        image: ImageView
    ) = clickListener(collectionModel, image)
}