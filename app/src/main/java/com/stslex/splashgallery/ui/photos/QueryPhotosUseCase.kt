package com.stslex.splashgallery.ui.photos

import androidx.paging.PagingSource
import com.stslex.splashgallery.data.photos.PhotosRepository
import com.stslex.splashgallery.data.photos.QueryPhotos
import com.stslex.splashgallery.ui.model.ImageUIModel
import javax.inject.Inject

class QueryPhotosUseCase @Inject constructor(
    private val repository: PhotosRepository
) {

    operator fun invoke(query: QueryPhotos): PagingSource<Int, ImageUIModel> {
        return repository.queryAll(query)
    }
}