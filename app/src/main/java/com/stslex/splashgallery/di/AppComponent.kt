package com.stslex.splashgallery.di

import com.stslex.splashgallery.data.data_source.RemoteSourceImpl
import com.stslex.splashgallery.data.repository.ImageRepositoryImpl
import com.stslex.splashgallery.ui.main_screen.MainViewModelFactory
import com.stslex.wallpape.ui.main_screen.MainFragment
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(fragment: MainFragment) {}

    val viewModelFactory: MainViewModelFactory
}

@Module
object AppModule {

    @Provides
    fun provideService() = RemoteSourceImpl()

    @Provides
    fun providesRepository(service: RemoteSourceImpl) = ImageRepositoryImpl(service)

    @Provides
    fun providesViewModelFactory(repository: ImageRepositoryImpl) = MainViewModelFactory(repository)
}
