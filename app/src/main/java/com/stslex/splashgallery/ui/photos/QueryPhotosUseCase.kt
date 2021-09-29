package com.stslex.splashgallery.ui.photos

import androidx.paging.PagingSource
import com.stslex.splashgallery.data.photos.AllPhotosRepository
import com.stslex.splashgallery.ui.model.image.ImageModel
import javax.inject.Inject

class QueryPhotosUseCase @Inject constructor(
    private val repository: AllPhotosRepository
) {

    operator fun invoke(query: List<String>): PagingSource<Int, ImageModel> {
        return repository.queryAll(query)
    }
}