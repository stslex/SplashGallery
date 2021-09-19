package com.stslex.splashgallery.di.component

import com.stslex.splashgallery.di.module.*
import com.stslex.splashgallery.utils.base.BaseFragment
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
        TestResponseModule::class,
        InteractorModule::class,
        MapperModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: BaseFragment)
}
