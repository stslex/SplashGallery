package com.stslex.core_model.data.image

import android.os.Parcelable
import com.stslex.core_model.data.user.UserModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sponsorship(
    val sponsor: UserModel
) : Parcelable