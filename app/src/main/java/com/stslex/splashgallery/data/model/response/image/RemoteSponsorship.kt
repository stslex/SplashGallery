package st.slex.csplashscreen.data.model.remote.image

import com.google.gson.annotations.SerializedName
import st.slex.csplashscreen.data.model.remote.user.RemoteUserModel

data class RemoteSponsorship(
    @SerializedName("sponsor")
    val sponsor: RemoteUserModel?
)