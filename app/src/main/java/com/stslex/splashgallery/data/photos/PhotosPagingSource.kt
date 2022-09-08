package com.stslex.splashgallery.data.photos

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.stslex.splashgallery.data.core.map
import com.stslex.splashgallery.ui.model.ImageUIModel
import com.stslex.splashgallery.ui.model.UserUIModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException

class PhotosPagingSource @AssistedInject constructor(
    private val service: PhotosService,
    @Assisted("query") private val query: QueryPhotos
) : PagingSource<Int, ImageUIModel>() {

    override fun getRefreshKey(state: PagingState<Int, ImageUIModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageUIModel> {
        Log.i("TestPhotos: ", query.toString())

        if (query is QueryPhotos.EmptyQuery) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }
        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER

            val response = when (query) {
                is QueryPhotos.AllPhotos -> service.getPhotos(pageNumber)
                is QueryPhotos.CollectionPhotos -> service.getCollectPhotos(query.query, pageNumber)
                is QueryPhotos.UserPhotos -> service.getUserPhotos(query.username, pageNumber)
                is QueryPhotos.UserLikes -> service.getUserLikes(query.username, pageNumber)
                is QueryPhotos.EmptyQuery ->
                    return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
            }

            return if (response.isSuccessful) {
                val photos = response.body()!!.map { it.map() }.map {
                    ImageUIModel(
                        id = it.id,
                        url = it.urls.regular,
                        user = UserUIModel(it.user.username, it.user.profile_image.medium)
                    )
                }
                val nextPageNumber = if (photos.isEmpty()) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
                LoadResult.Page(photos, prevPageNumber, nextPageNumber)
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("query") query: QueryPhotos): PhotosPagingSource
    }

    companion object {
        private const val INITIAL_PAGE_NUMBER = 1
    }
}