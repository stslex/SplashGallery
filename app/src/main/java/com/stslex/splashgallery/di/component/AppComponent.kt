package com.stslex.splashgallery.di.component

import android.app.Application
import com.stslex.splashgallery.GalleryApplication
import com.stslex.splashgallery.di.module.ActivityModule
import com.stslex.splashgallery.di.module.ApplicationModule
import com.stslex.splashgallery.di.module.RemoteSourceModule
import com.stslex.splashgallery.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        RemoteSourceModule::class,
        ActivityModule::class,
        ViewModelModule::class
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
