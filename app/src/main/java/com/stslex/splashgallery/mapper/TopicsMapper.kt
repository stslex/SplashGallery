package com.stslex.splashgallery.mapper

import com.stslex.splashgallery.data.model.remote.RemoteTopicsModel
import com.stslex.splashgallery.data.model.domain.title.TopicsModel

class TopicsMapper : BaseMapper<RemoteTopicsModel, TopicsModel> {
    override fun transformToDomain(type: RemoteTopicsModel): TopicsModel = TopicsModel(
        id = type.id,
        slug = type.slug,
        title = type.title
    )
}