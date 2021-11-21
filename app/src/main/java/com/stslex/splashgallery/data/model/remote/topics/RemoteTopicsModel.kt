package st.slex.csplashscreen.data.model.remote.topics

import com.google.gson.annotations.SerializedName
import st.slex.csplashscreen.data.model.remote.collection.RemoteLinksCollectionModel
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.data.model.remote.user.RemoteUserModel

data class RemoteTopicsModel(
    @SerializedName("id") val id: String? = "",
    @SerializedName("slug") val slug: String? = "",
    @SerializedName("title") val title: String? = "",
    @SerializedName("description") val description: String? = "",
    @SerializedName("published_at") val published_at: String? = "",
    @SerializedName("updated_at") val updated_at: String? = "",
    @SerializedName("starts_at") val starts_at: String? = "",
    @SerializedName("ends_at") val ends_at: String? = "",
    @SerializedName("only_submissions_after") val only_submissions_after: String? = "",
    @SerializedName("featured") val featured: String? = "",
    @SerializedName("total_photos") val total_photos: String? = "",
    @SerializedName("links") val links: RemoteLinksCollectionModel,
    @SerializedName("status") val status: String? = "",
    @SerializedName("owners") val owners: List<RemoteUserModel>?,
    @SerializedName("cover_photo") val cover_photo: RemoteImageModel?,
    @SerializedName("preview_photos") val preview_photos: List<RemotePreviewPhotosModel>?
)