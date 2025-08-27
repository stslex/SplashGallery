package com.stslex.splashgallery.feature_collections.di.component

import android.content.Context
import com.stslex.splashgallery.feature_collections.ui.CollectionsFragment
import com.stslex.splashgallery.feature_collections.di.module.CoroutineModule
import com.stslex.splashgallery.feature_collections.di.module.NetworkServiceModule
import com.stslex.splashgallery.feature_collections.di.module.UtilsModule
import com.stslex.splashgallery.feature_collections.di.module.ViewModelFactoryModule
import com.stslex.splashgallery.feature_collections.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        NetworkServiceModule::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        UtilsModule::class,
        CoroutineModule::class
    ]
)
interface CollectionsComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder
        fun create(): CollectionsComponent
    }

    fun inject(fragment: CollectionsFragment)
}