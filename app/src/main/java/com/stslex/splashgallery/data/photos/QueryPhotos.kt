package com.stslex.splashgallery.data.photos

sealed class QueryPhotos {
    class CollectionPhotos(val query: String) : QueryPhotos()
    class UserPhotos(val username: String) : QueryPhotos()
    class UserLikes(val username: String) : QueryPhotos()
    object AllPhotos : QueryPhotos()
    object EmptyQuery : QueryPhotos()
}
