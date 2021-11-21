package st.slex.csplashscreen.data.model.remote.statistic

import com.google.gson.annotations.SerializedName

data class RemoteValue(
    @SerializedName("date")
    val date: String,
    @SerializedName("value")
    val value: Int
)