package com.stslex.splashgallery.data.photos

import androidx.paging.PagingSource
import com.stslex.splashgallery.ui.model.ImageUIModel
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val photosPagingSourceFactory: PhotosPagingSource.Factory
) {

    fun queryAll(query: QueryPhotos): PagingSource<Int, ImageUIModel> {
        return photosPagingSourceFactory.create(query)
    }
}