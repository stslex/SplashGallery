package st.slex.csplashscreen.data.model.remote.topics

import com.google.gson.annotations.SerializedName
import st.slex.csplashscreen.data.model.remote.image.RemoteUrlsModel

data class RemotePreviewPhotosModel(
    @SerializedName("id") val id: String? = "",
    @SerializedName("created_at") val created_at: String? = "",
    @SerializedName("updated_at") val updated_at: String? = "",
    @SerializedName("urls") val urls: RemoteUrlsModel?
)