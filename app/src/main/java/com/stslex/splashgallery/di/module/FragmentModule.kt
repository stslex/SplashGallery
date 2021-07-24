package com.stslex.splashgallery.di.module

import com.stslex.wallpape.ui.main_screen.MainFragment
import com.stslex.wallpape.ui.main_screen_pager.MainPagerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

}