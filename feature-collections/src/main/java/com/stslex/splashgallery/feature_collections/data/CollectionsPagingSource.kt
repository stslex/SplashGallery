package com.stslex.splashgallery.feature_collections.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.stslex.splashgallery.core_model.data.collection.CollectionModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException

class CollectionsPagingSource @AssistedInject constructor(
    private val service: CollectionService,
    @Assisted("query") private val query: QueryCollections
) : PagingSource<Int, CollectionModel>() {

    override fun getRefreshKey(state: PagingState<Int, CollectionModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CollectionModel> {
        Log.i("TestPhotos: ", query.toString())

        if (query is QueryCollections.EmptyQuery) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }
        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER

            val response = when (query) {
                is QueryCollections.AllCollections ->
                    service.getCollections(pageNumber)
                is QueryCollections.UserCollections ->
                    service.getCollections(query.query, pageNumber)
                is QueryCollections.EmptyQuery ->
                    return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
            }

            return if (response.isSuccessful) {
                val photos = response.body()!!.map { it.map() }
                Log.i("TestPhotos Size: ", photos.size.toString())

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
        fun create(@Assisted("query") query: QueryCollections): CollectionsPagingSource
    }

    companion object {
        private const val INITIAL_PAGE_NUMBER = 1
    }
}