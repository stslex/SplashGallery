package com.stslex.splashgallery.data.photos

import androidx.paging.PagingSource
import com.stslex.splashgallery.data.core.QueryPhotos
import com.stslex.splashgallery.ui.model.image.ImageModel
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val photosPagingSourceFactory: PhotosPagingSource.Factory
) {

    fun queryAll(query: QueryPhotos): PagingSource<Int, ImageModel> {
        return photosPagingSourceFactory.create(query)
    }
}