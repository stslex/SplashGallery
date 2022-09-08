package st.slex.csplashscreen.data.model.remote.image

import com.google.gson.annotations.SerializedName

data class RemotePositionModel(
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?
)
