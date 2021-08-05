package com.stslex.splashgallery.di.component

import com.stslex.splashgallery.di.module.*
import com.stslex.splashgallery.ui.all_photos.AllPhotosFragment
import com.stslex.splashgallery.ui.main_screen.MainFragment
import com.stslex.splashgallery.ui.photo_details.PhotoDetailsFragment
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragment
import com.stslex.splashgallery.ui.user.UserFragment
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
    fun inject(fragment: MainFragment)
    fun inject(fragment: SingleCollectionFragment)
    fun inject(fragment: AllPhotosFragment)
    fun inject(fragment: PhotoDetailsFragment)
    fun inject(fragment: UserFragment)
}
