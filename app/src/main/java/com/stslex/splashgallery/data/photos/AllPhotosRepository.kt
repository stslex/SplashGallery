package com.stslex.splashgallery.data.photos

import androidx.paging.PagingSource
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import javax.inject.Inject

class AllPhotosRepository @Inject constructor(
    private val photosPagingSourceFactory: PhotosPagingSource.Factory
) {

    fun queryAll(query: List<String>): PagingSource<Int, ImageModel> {
        return photosPagingSourceFactory.create(query)
    }
}