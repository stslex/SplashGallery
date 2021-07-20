package com.stslex.splashgallery.mapper

import com.stslex.splashgallery.data.model.title.RemoteTopicsModel
import com.stslex.splashgallery.data.model.title.TopicsModel

class TopicsMapper : BaseMapper<RemoteTopicsModel, TopicsModel> {
    override fun transformToDomain(type: RemoteTopicsModel): TopicsModel = TopicsModel(
        id = type.id,
        slug = type.slug,
        title = type.title
    )
}