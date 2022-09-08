package com.stslex.feature_collections.di.module

import com.stslex.core_coroutines.AppDispatcher
import com.stslex.core_coroutines.AppDispatcherImpl
import dagger.Binds
import dagger.Module

@Module
interface CoroutineModule {
    @Binds
    fun bindsCoroutineDispatchers(dispatcherImpl: AppDispatcherImpl): AppDispatcher
}