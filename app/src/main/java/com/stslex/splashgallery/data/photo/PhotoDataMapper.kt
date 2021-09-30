package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.domain.photo.PhotoDomain
import com.stslex.splashgallery.domain.photo.PhotoDomainResult
import javax.inject.Inject


interface PhotoDataMapper<T> : Abstract.Mapper.DataToDomain<PhotoData, T> {

    class Base @Inject constructor() : PhotoDataMapper<PhotoDomainResult> {
        override fun map(data: PhotoData): PhotoDomainResult =
            PhotoDomainResult.Success(
                with(data) {
                    PhotoDomain.Base(
                        imageId = imageId(),
                        imageUrl = imageUrl(),
                        userId = userId(),
                        userName = userName(),
                        userUrl = userUrl(),
                        exif = PhotoDomain.Base.ExifDomain(
                            make = make(),
                            model = model(),
                            exposure_time = exposureTime(),
                            aperture = aperture(),
                            focal_length = focalLength(),
                            iso = iso()
                        )

                    )
                }
            )

        override fun map(exception: Exception): PhotoDomainResult =
            PhotoDomainResult.Failure(exception)

    }
}