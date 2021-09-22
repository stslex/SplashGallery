package com.stslex.splashgallery.core.model.domain.image

import android.os.Parcelable
import com.stslex.splashgallery.core.model.domain.user.UserModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sponsorship(
    val sponsor: UserModel?
) : Parcelable