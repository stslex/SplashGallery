package com.stslex.splashgallery.data.model.newmodel


data class PhotoStatistics(
    val id: String,
    val downloads: Downloads,
    val views: Views,
    val likes: Likes
)

data class UserStatistics(
    val username: String,
    val downloads: Downloads,
    val views: Views,
    val likes: Likes
)

data class Downloads(
    val total: Int,
    val historical: Historical
)

data class Views(
    val total: Int,
    val historical: Historical
)

data class Likes(
    val total: Int,
    val historical: Historical
)

data class Historical(
    val change: Int,
    val resolution: String,
    val quality: String,
    val values: List<Value>
)

data class Value(
    val date: String,
    val value: Int
)