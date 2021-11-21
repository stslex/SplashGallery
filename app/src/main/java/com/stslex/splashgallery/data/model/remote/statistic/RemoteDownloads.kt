package st.slex.csplashscreen.data.model.remote.statistic

import com.google.gson.annotations.SerializedName

data class RemoteDownloads(
    @SerializedName("total")
    val total: Int,
    @SerializedName("historical")
    val historical: RemoteHistorical
)
