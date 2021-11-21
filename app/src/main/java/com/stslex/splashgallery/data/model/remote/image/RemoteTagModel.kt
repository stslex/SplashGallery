package st.slex.csplashscreen.data.model.remote.image

import com.google.gson.annotations.SerializedName

data class RemoteTagModel(
    @SerializedName("type")
    val type: String?,
    @SerializedName("title")
    val title: String?
)
