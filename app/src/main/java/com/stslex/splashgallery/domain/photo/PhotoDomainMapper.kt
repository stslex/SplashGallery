package com.stslex.splashgallery.domain.photo

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.ui.detail_photo.PhotoUIResult
import com.stslex.splashgallery.ui.photos.PhotosUI
import javax.inject.Inject

interface PhotoDomainMapper<T> : Abstract.Mapper.DomainToUi<PhotoDomain, T> {

    class Base @Inject constructor() : PhotoDomainMapper<PhotoUIResult> {
        override fun map(data: PhotoDomain): PhotoUIResult = with(data) {
            PhotoUIResult.Success(
                PhotosUI.Base(
                    imageId = imageId(),
                    imageUrl = imageUrl(),
                    userId = userId(),
                    userName = userName(),
                    userUrl = userUrl(),
                    exif = PhotosUI.Base.ExifUI(
                        make = make(),
                        model = model(),
                        exposure_time = exposureTime(),
                        aperture = aperture(),
                        focal_length = focalLength(),
                        iso = iso()
                    )
                )
            )
        }

        override fun map(exception: Exception): PhotoUIResult =
            PhotoUIResult.Failure(exception)
    }
}