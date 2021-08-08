package com.stslex.splashgallery.ui.user.pager_view_models

import androidx.lifecycle.LiveData
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.utils.base.BaseSharedCollectionsViewModel

class UserCollectionSharedViewModel : BaseSharedCollectionsViewModel() {
    override val collection: LiveData<List<CollectionModel>>
        get() = super.collection
    override val numberCollections: LiveData<Int>
        get() = super.numberCollections

}