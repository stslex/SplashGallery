package com.stslex.splashgallery.di.component

import android.app.Application
import com.stslex.splashgallery.di.module.*
import com.stslex.splashgallery.ui.core.BaseFragment
import dagger.BindsInstance
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
        MapperModule::class,
        ImageModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: BaseFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun create(): AppComponent
    }
}
