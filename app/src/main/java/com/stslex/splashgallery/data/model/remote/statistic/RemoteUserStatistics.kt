package st.slex.csplashscreen.data.model.remote.statistic

import com.google.gson.annotations.SerializedName


data class RemoteUserStatistics(
    @SerializedName("username")
    val username: String,
    @SerializedName("downloads")
    val downloads: RemoteDownloads,
    @SerializedName("views")
    val views: RemoteViews,
    @SerializedName("likes")
    val likes: RemoteLikes
)
