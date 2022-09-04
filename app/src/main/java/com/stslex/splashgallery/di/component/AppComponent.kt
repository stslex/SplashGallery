package com.stslex.splashgallery.di.component

import android.app.Application
import com.stslex.splashgallery.di.module.*
import com.stslex.splashgallery.ui.collections.CollectionsFragment
import com.stslex.splashgallery.ui.detail_photo.PhotoDetailsFragment
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.photos.PhotosFragment
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragment
import com.stslex.splashgallery.ui.single_photo.SingleImageFragment
import com.stslex.splashgallery.ui.user.UserFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        NetworkServiceModule::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        RepositoryModule::class,
        ResponseModule::class,
        MappersModule::class,
        UtilsModule::class,
        CoroutineModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun create(): AppComponent
    }

    fun inject(fragment: CollectionsFragment)
    fun inject(fragment: UserFragment)
    fun inject(fragment: MainFragment)
    fun inject(fragment: PhotoDetailsFragment)
    fun inject(fragment: PhotosFragment)
    fun inject(fragment: SingleCollectionFragment)
    fun inject(fragment: SingleImageFragment)
}
