package com.stslex.splashgallery.di.component

import com.stslex.splashgallery.GalleryApplication
import com.stslex.splashgallery.di.module.RemoteSourceModule
import com.stslex.splashgallery.di.module.RepositoryModule
import com.stslex.splashgallery.di.module.ViewModelModule
import com.stslex.wallpape.ui.main_screen.MainFragment
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
    ]
)
interface AppComponent {
    fun inject(application: GalleryApplication)
    fun inject(fragment: MainFragment)
}
