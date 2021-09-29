package com.stslex.splashgallery.data.model.image

import com.google.gson.annotations.SerializedName
import com.stslex.splashgallery.data.model.user.RemoteUserModel

data class RemoteSponsorship(
    @SerializedName("sponsor")
    val sponsor: RemoteUserModel?
)