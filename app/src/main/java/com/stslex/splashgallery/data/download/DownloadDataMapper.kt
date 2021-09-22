package com.stslex.splashgallery.data.download

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.domain.download.DownloadDomain
import com.stslex.splashgallery.domain.download.DownloadDomainResult
import javax.inject.Inject

interface DownloadDataMapper<T> : Abstract.Mapper.DataToDomain<DownloadData, T> {

    class Base @Inject constructor() : DownloadDataMapper<DownloadDomainResult> {
        override fun map(data: DownloadData): DownloadDomainResult =
            DownloadDomainResult.Success(DownloadDomain.Base(data.url()))

        override fun map(exception: String): DownloadDomainResult =
            DownloadDomainResult.Failure(exception)

    }
}