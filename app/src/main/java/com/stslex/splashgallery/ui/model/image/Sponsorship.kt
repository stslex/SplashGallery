package com.stslex.splashgallery.ui.model.image

import android.os.Parcelable
import com.stslex.splashgallery.ui.model.user.UserModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sponsorship(
    val sponsor: UserModel?
) : Parcelable