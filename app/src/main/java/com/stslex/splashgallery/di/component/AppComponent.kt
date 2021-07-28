package com.stslex.splashgallery.di.component

import com.stslex.splashgallery.di.module.AppModule
import com.stslex.splashgallery.di.module.NetworkServiceModule
import com.stslex.splashgallery.ui.single_collection.SingleCollectionFragment
import com.stslex.wallpape.ui.main_screen.MainFragment
import dagger.Component

@Component(modules = [NetworkServiceModule::class, AppModule::class])
interface AppComponent {
    fun inject(fragment: MainFragment)
    fun inject(fragment: SingleCollectionFragment)
}
