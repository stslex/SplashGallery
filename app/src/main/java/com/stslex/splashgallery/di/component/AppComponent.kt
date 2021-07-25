package com.stslex.splashgallery.di.component

import com.stslex.splashgallery.di.module.AppModule
import com.stslex.splashgallery.di.module.NetworkServiceModule
import com.stslex.wallpape.ui.main_screen.MainFragment
import com.stslex.wallpape.ui.main_screen_pager.MainPagerFragment
import dagger.Component

@Component(modules = [NetworkServiceModule::class, AppModule::class])
interface AppComponent {
    fun inject(fragment: MainFragment)
    fun inject(fragment: MainPagerFragment)
}
