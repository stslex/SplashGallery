package com.stslex.feature_collections.di.module

import com.stslex.feature_collections.data.CollectionService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [RetrofitModule::class])
class NetworkServiceModule {

    @Provides
    fun providesCollectionService(retrofit: Retrofit): CollectionService =
        retrofit.create(CollectionService::class.java)
}