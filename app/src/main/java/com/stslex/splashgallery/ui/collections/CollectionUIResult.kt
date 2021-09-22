package com.stslex.splashgallery.ui.collections

sealed class CollectionUIResult {
    class Success(val data: List<CollectionUI>) : CollectionUIResult()
    class Failure(val exception: String) : CollectionUIResult()
    object Loading : CollectionUIResult()
}
