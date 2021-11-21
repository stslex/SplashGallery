package st.slex.csplashscreen.data.model.remote.image

import com.google.gson.annotations.SerializedName
import st.slex.csplashscreen.data.model.remote.collection.RemoteCollectionModel
import st.slex.csplashscreen.data.model.remote.statistic.RemotePhotoStatistics
import st.slex.csplashscreen.data.model.remote.user.RemoteUserModel

class RemoteImageModel(
    @SerializedName("id") val id: String,
    @SerializedName("created_at") val created_at: String?,
    @SerializedName("updated_at") val updated_at: String?,
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("color") val color: String? = "#E0E0E0",
    @SerializedName("blur_hash") val blur_hash: String?,
    @SerializedName("views") val views: Int?,
    @SerializedName("downloads") val downloads: Int?,
    @SerializedName("likes") val likes: Int?,
    @SerializedName("liked_by_user") var liked_by_user: Boolean?,
    @SerializedName("description") val description: String?,
    @SerializedName("alt_description") val alt_description: String?,
    @SerializedName("exif") val exif: RemoteExifModel?,
    @SerializedName("location") val location: RemoteLocationModel?,
    @SerializedName("tags") val tags: List<RemoteTagModel>?,
    @SerializedName("current_user_collections") val current_user_collections: List<RemoteCollectionModel>?,
    @SerializedName("sponsorship") val sponsorship: RemoteSponsorship?,
    @SerializedName("urls") val urls: RemoteUrlsModel,
    @SerializedName("links") val links: RemoteLinksImageModel?,
    @SerializedName("user") val user: RemoteUserModel?,
    @SerializedName("statistics") val statistics: RemotePhotoStatistics?
)