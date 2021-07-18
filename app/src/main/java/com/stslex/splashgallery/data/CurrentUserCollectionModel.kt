package com.stslex.splashgallery.data

data class CurrentUserCollectionModel(
    val id: String,
    val title: String,
    val published_at: String,
    val last_collected_at: String,
    val updated_at: String,
    val cover_photo: String,
    val user: String,
)