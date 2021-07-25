package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.data_source.retrofit.RetrofitClient
import com.stslex.splashgallery.data.data_source.retrofit.RetrofitService
import com.stslex.splashgallery.utils.BASE_URL
import dagger.Module
import dagger.Provides

@Module
class NetworkServiceModule {
    @Provides
    fun providesRetrofitService(): RetrofitService = RetrofitClient().getClient(BASE_URL)
        .create(RetrofitService::class.java)
}