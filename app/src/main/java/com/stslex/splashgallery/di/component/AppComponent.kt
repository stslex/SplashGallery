package com.stslex.splashgallery.di.component

import com.stslex.splashgallery.di.module.*
import com.stslex.splashgallery.utils.base.BaseFragment
import dagger.Component

@Component(
    modules = [
        NetworkServiceModule::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        RepositoryModule::class,
        SourceModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: BaseFragment)
}
