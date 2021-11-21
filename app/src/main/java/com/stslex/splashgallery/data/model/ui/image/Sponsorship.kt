package com.stslex.splashgallery.data.model.ui.image

import android.os.Parcelable
import com.stslex.splashgallery.data.model.ui.user.UserModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sponsorship(
    val sponsor: UserModel
) : Parcelable