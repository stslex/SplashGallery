package com.stslex.splashgallery.domain.download

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.ui.detail_photo.DownloadUI
import com.stslex.splashgallery.ui.detail_photo.DownloadUIResult
import javax.inject.Inject

interface DownloadDomainMapper<T> : Abstract.Mapper.DomainToUi<DownloadDomain, T> {

    class Base @Inject constructor() : DownloadDomainMapper<DownloadUIResult> {

        override fun map(data: DownloadDomain): DownloadUIResult =
            DownloadUIResult.Success(DownloadUI.Base(url = data.url()))

        override fun map(exception: String): DownloadUIResult = DownloadUIResult.Failure(exception)
    }
}