package com.stslex.splashgallery.di.component

import android.app.Application
import com.stslex.splashgallery.GalleryApplication
import com.stslex.splashgallery.di.module.RemoteSourceModule
import com.stslex.splashgallery.di.module.RepositoryModule
import com.stslex.splashgallery.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        RemoteSourceModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: GalleryApplication)
}
