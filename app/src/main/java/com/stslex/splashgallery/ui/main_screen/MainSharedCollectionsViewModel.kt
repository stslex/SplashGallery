package com.stslex.splashgallery.ui.main_screen

import androidx.lifecycle.LiveData
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.utils.base.BaseSharedCollectionsViewModel

class MainSharedCollectionsViewModel : BaseSharedCollectionsViewModel() {
    override val collection: LiveData<List<CollectionModel>>
        get() = super.collection
    override val numberCollections: LiveData<Int>
        get() = super.numberCollections

}