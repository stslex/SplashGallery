package com.stslex.splashgallery.di.component

import com.stslex.splashgallery.di.module.NetworkServiceModule
import com.stslex.splashgallery.di.module.RepositoryModule
import com.stslex.splashgallery.di.module.ViewModelFactoryModule
import com.stslex.splashgallery.di.module.ViewModelModule
import com.stslex.splashgallery.utils.base.BaseFragment
import dagger.Component

@Component(
    modules = [
        NetworkServiceModule::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: BaseFragment)
}
