package com.stslex.splashgallery.data

import com.stslex.splashgallery.data.model.collection.RemoteCollectionModel
import com.stslex.splashgallery.data.model.collection.RemoteLinksCollectionModel
import com.stslex.splashgallery.data.model.image.*
import com.stslex.splashgallery.data.model.statistic.*
import com.stslex.splashgallery.data.model.user.RemoteBadgeModel
import com.stslex.splashgallery.data.model.user.RemoteProfileImageModel
import com.stslex.splashgallery.data.model.user.RemoteUserLinksModel
import com.stslex.splashgallery.data.model.user.RemoteUserModel
import com.stslex.splashgallery.ui.model.*
import com.stslex.splashgallery.ui.model.collection.CollectionModel
import com.stslex.splashgallery.ui.model.collection.LinksCollectionModel
import com.stslex.splashgallery.ui.model.image.*
import com.stslex.splashgallery.ui.model.user.BadgeModel
import com.stslex.splashgallery.ui.model.user.ProfileImageModel
import com.stslex.splashgallery.ui.model.user.UserLinksModel
import com.stslex.splashgallery.ui.model.user.UserModel

internal fun RemoteImageModel.toImageModel(): ImageModel = ImageModel(
    id = id,
    created_at = created_at ?: "",
    updated_at = updated_at ?: "",
    width = width ?: 0,
    height = height ?: 0,
    color = color ?: "",
    blur_hash = blur_hash ?: "",
    views = views ?: 0,
    downloads = downloads ?: 0,
    likes = likes ?: 0,
    liked_by_user = liked_by_user ?: false,
    description = description ?: "",
    alt_description = alt_description ?: "",
    exif = exif?.toExifModel(),
    location = location?.toLocationModel(),
    tags = tags?.map { it.toTagModel() },
    current_user_collections = current_user_collections?.map { it.toCollectionModel() },
    sponsorship = sponsorship?.toSponsorship(),
    urls = urls.toUrlsModel(),
    links = links?.toLinksImageModel(),
    user = user?.toUserModel(),
    statistics = statistics?.toPhotoStatistic()
)

internal fun RemoteExifModel.toExifModel(): ExifModel =
    ExifModel(
        make = make ?: "",
        model = model ?: "",
        exposure_time = exposure_time ?: "",
        aperture = aperture ?: "",
        focal_length = focal_length ?: "",
        iso = iso ?: 0
    )

internal fun RemoteLocationModel.toLocationModel(): LocationModel =
    LocationModel(
        city = city ?: "",
        country = country ?: "",
        position = position?.toPositionModel()
    )

internal fun RemoteTagModel.toTagModel(): TagModel =
    TagModel(type = type ?: "", title = title ?: "")

internal fun RemoteCollectionModel.toCollectionModel(): CollectionModel =
    CollectionModel(
        id = id,
        title = title,
        description = description ?: "",
        published_at = published_at ?: "",
        updated_at = updated_at ?: "",
        curated = curated ?: false,
        featured = featured ?: false,
        total_photos = total_photos,
        private = private ?: false,
        share_key = share_key ?: "",
        tags = tags?.map { it.toTagModel() },
        cover_photo = cover_photo?.toImageModel(),
        preview_photos = preview_photos?.map { it.toImageModel() },
        user = user?.toUserModel(),
        links = links?.toLinksCollectionModel()
    )

internal fun RemoteSponsorship.toSponsorship(): Sponsorship =
    Sponsorship(sponsor = sponsor?.toUserModel())

internal fun RemoteUrlsModel.toUrlsModel(): UrlsModel =
    UrlsModel(
        raw = raw,
        full = full,
        regular = regular,
        small = small,
        thumb = thumb
    )

internal fun RemoteLinksImageModel.toLinksImageModel(): LinksImageModel =
    LinksImageModel(
        self = self,
        html = html,
        download = download,
        download_location = download_location
    )

internal fun RemoteUserModel.toUserModel(): UserModel =
    UserModel(
        id = id,
        updated_at = updated_at ?: "",
        username = username ?: "",
        name = name ?: "",
        first_name = first_name ?: "",
        last_name = last_name ?: "",
        instagram_username = instagram_username ?: "",
        twitter_username = twitter_username ?: "",
        portfolio_url = portfolio_url ?: "",
        bio = bio ?: "",
        location = location ?: "",
        total_likes = total_likes ?: 0,
        total_photos = total_photos ?: 0,
        total_collections = total_collections ?: 0,
        followed_by_user = followed_by_user ?: false,
        followers_count = followers_count ?: 0,
        following_count = following_count ?: 0,
        downloads = downloads ?: 0,
        profile_image = profile_image?.toProfileImageModel(),
        badge = badge?.toBadgeModel(),
        links = links?.toUserLinksModel(),
        photos = photos?.map { it.toImageModel() }
    )

internal fun RemotePhotoStatistics.toPhotoStatistic(): PhotoStatistics =
    PhotoStatistics(
        id = id,
        downloads = downloads.toDownloads(),
        views = views.toViews(),
        likes = likes.toLikes()
    )

internal fun RemotePositionModel.toPositionModel(): PositionModel =
    PositionModel(latitude = latitude ?: 0.0, longitude = longitude ?: 0.0)

internal fun RemoteLinksCollectionModel.toLinksCollectionModel(): LinksCollectionModel =
    LinksCollectionModel(self = self, html = html, photos = photos)

internal fun RemoteProfileImageModel.toProfileImageModel(): ProfileImageModel =
    ProfileImageModel(small = small, medium = medium, large = large)

internal fun RemoteBadgeModel.toBadgeModel(): BadgeModel =
    BadgeModel(title = title ?: "", primary = primary ?: false, slug = slug ?: "", link = link)

internal fun RemoteUserLinksModel.toUserLinksModel(): UserLinksModel =
    UserLinksModel(
        self = self,
        html = html,
        photos = photos,
        likes = likes,
        portfolio = portfolio,
        following = following,
        followers = followers
    )

internal fun RemoteDownloads.toDownloads(): Downloads =
    Downloads(total = total, historical = historical.toHistorical())

internal fun RemoteViews.toViews(): Views =
    Views(total = total, historical = historical.toHistorical())

internal fun RemoteLikes.toLikes(): Likes =
    Likes(total = total, historical = historical.toHistorical())

internal fun RemoteHistorical.toHistorical(): Historical =
    Historical(
        change = change,
        resolution = resolution,
        quality = quality,
        values = values.map { it.toValue() }
    )

internal fun RemoteValue.toValue(): Value = Value(date = date, value = value)
