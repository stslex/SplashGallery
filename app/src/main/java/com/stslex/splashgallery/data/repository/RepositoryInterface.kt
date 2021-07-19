package com.stslex.splashgallery.data.repository

import com.stslex.splashgallery.data.model.PagesModel
import com.stslex.splashgallery.utils.Result

interface RepositoryInterface {

    suspend fun getPageFromRetrofit(pageNumber: Int): Result<PagesModel>
}