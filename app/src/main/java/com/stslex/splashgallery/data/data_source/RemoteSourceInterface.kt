package com.stslex.splashgallery.data.data_source

import com.stslex.splashgallery.data.model.PagesModel
import com.stslex.splashgallery.data.model.title.TopicsModel
import com.stslex.splashgallery.utils.Result

interface RemoteSourceInterface {
    suspend fun getAllPhotos(pageNumber: Int): Result<PagesModel>
    suspend fun getTopics(): Result<List<TopicsModel>>
    suspend fun getSingleTopic(t_id: String, pageNumber: Int): Result<PagesModel>
}