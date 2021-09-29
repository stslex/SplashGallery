package com.stslex.splashgallery.data.model.statistic

import com.google.gson.annotations.SerializedName

data class RemoteViews(
    @SerializedName("total")
    val total: Int,
    @SerializedName("historical")
    val historical: RemoteHistorical
)
