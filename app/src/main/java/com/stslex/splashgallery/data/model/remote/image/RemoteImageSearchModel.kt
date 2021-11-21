package st.slex.csplashscreen.data.model.remote.image

import com.google.gson.annotations.SerializedName

data class RemoteImageSearchModel(
    val total: Int?,
    val total_pages: Int?,
    @SerializedName("results")
    val results: List<RemoteImageModel>
)