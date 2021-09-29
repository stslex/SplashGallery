package com.stslex.splashgallery.data.model.domain.image

import android.os.Parcelable
import com.stslex.splashgallery.data.model.domain.user.UserModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sponsorship(
    val sponsor: UserModel?
) : Parcelable