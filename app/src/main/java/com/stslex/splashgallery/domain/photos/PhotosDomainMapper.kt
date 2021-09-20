package com.stslex.splashgallery.domain.photos

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.domain.photo.PhotoDomain
import com.stslex.splashgallery.ui.photos.PhotosUI
import com.stslex.splashgallery.ui.photos.PhotosUIResult
import javax.inject.Inject

interface PhotosDomainMapper<T> : Abstract.Mapper.DomainToUi<List<PhotoDomain>, T> {

    class Base @Inject constructor() : PhotosDomainMapper<PhotosUIResult> {
        override fun map(data: List<PhotoDomain>): PhotosUIResult =
            PhotosUIResult.Success(data.map {
                PhotosUI.Base(
                    imageId = it.imageId(),
                    imageUrl = it.imageUrl(),
                    userId = it.userId(),
                    userName = it.userName(),
                    userUrl = it.userUrl(),
                    exif = with(it.getExifDomain()) {
                        PhotosUI.Base.ExifUI(
                            make = make(),
                            model = model(),
                            exposure_time = exposureTime(),
                            aperture = aperture(),
                            focal_length = focalLength(),
                            iso = iso()
                        )
                    }
                )
            })

        override fun map(exception: String): PhotosUIResult =
            PhotosUIResult.Failure(exception)
    }
}