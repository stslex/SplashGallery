package com.stslex.splashgallery.di

import com.stslex.splashgallery.data.data_source.RemoteSource
import com.stslex.splashgallery.data.repository.Repository
import com.stslex.splashgallery.ui.main_screen.MainViewModelFactory
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class])
interface AppComponent{
    val viewModelFactory: MainViewModelFactory
}

@Module
object AppModule {

    @Provides
    fun provideService() = RemoteSource()

    @Provides
    fun providesRepository(service: RemoteSource) = Repository(service)

    @Provides
    fun providesViewModelFactory(repository: Repository) = MainViewModelFactory(repository)
}
