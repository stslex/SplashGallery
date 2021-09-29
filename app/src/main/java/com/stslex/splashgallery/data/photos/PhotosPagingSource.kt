package com.stslex.splashgallery.data.photos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import retrofit2.HttpException
import javax.inject.Inject

class PhotosPagingSource @Inject constructor(
    private val service: AllPhotosService,
) : PagingSource<Int, ImageModel>() {

    override fun getRefreshKey(state: PagingState<Int, ImageModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageModel> {
        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize = params.loadSize
            val response = service.getPhotos(pageNumber, pageSize, API_KEY_SUCCESS)

            return if (response.isSuccessful) {
                val photos = response.body()!!
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

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}