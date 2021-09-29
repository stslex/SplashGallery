package com.stslex.splashgallery.di.component

import com.stslex.splashgallery.di.module.*
import com.stslex.splashgallery.ui.core.BaseFragment
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Component(
    modules = [
        NetworkServiceModule::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        RepositoryModule::class,
        ResponseModule::class,
        InteractorModule::class,
        MapperModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: BaseFragment)
}
