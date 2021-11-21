package st.slex.csplashscreen.data.model.remote.collection

import com.google.gson.annotations.SerializedName
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.data.model.remote.image.RemoteTagModel
import st.slex.csplashscreen.data.model.remote.user.RemoteUserModel

data class RemoteCollectionModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("published_at")
    val published_at: String?,
    @SerializedName("updated_at")
    val updated_at: String?,
    @SerializedName("curated")
    val curated: Boolean?,
    @SerializedName("featured")
    val featured: Boolean?,
    @SerializedName("total_photos")
    val total_photos: Int,
    @SerializedName("private")
    val private: Boolean?,
    @SerializedName("share_key")
    val share_key: String?,
    @SerializedName("tags")
    val tags: List<RemoteTagModel>?,
    @SerializedName("cover_photo")
    val cover_photo: RemoteImageModel?,
    @SerializedName("preview_photos")
    val preview_photos: List<RemoteImageModel>?,
    @SerializedName("user")
    val user: RemoteUserModel?,
    @SerializedName("links")
    val links: RemoteLinksCollectionModel?
)
