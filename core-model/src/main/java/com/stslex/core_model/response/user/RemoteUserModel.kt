package st.slex.csplashscreen.data.model.remote.user

import com.google.gson.annotations.SerializedName
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel

data class RemoteUserModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("updated_at")
    val updated_at: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("first_name")
    val first_name: String?,
    @SerializedName("last_name")
    val last_name: String?,
    @SerializedName("instagram_username")
    val instagram_username: String?,
    @SerializedName("twitter_username")
    val twitter_username: String?,
    @SerializedName("portfolio_url")
    val portfolio_url: String?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("total_likes")
    val total_likes: Int?,
    @SerializedName("total_photos")
    val total_photos: Int?,
    @SerializedName("total_collections")
    val total_collections: Int?,
    @SerializedName("followed_by_user")
    val followed_by_user: Boolean?,
    @SerializedName("followers_count")
    val followers_count: Int?,
    @SerializedName("following_count")
    val following_count: Int?,
    @SerializedName("downloads")
    val downloads: Int?,
    @SerializedName("profile_image")
    val profile_image: RemoteProfileImageModel?,
    @SerializedName("badge")
    val badge: RemoteBadgeModel?,
    @SerializedName("links")
    val links: RemoteUserLinksModel?,
    @SerializedName("photos")
    val photos: List<RemoteImageModel>?
)