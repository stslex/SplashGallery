package com.stslex.splashgallery.ui.photos

import androidx.paging.PagingSource
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.photos.AllPhotosRepository
import javax.inject.Inject

class QueryPhotosUseCase @Inject constructor(
    private val repository: AllPhotosRepository
) {

    operator fun invoke(query: List<String>): PagingSource<Int, ImageModel> {
        return repository.queryAll(query)
    }
}