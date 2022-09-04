package com.stslex.feature_collections.di.component

import android.app.Application
import android.content.Context
import com.stslex.feature_collections.di.module.*
import com.stslex.feature_collections.ui.CollectionsFragment
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