package com.stslex.splashgallery.data.model.data.image

import android.os.Parcelable
import com.stslex.splashgallery.data.model.data.user.UserModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sponsorship(
    val sponsor: UserModel
) : Parcelable