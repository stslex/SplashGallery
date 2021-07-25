package com.stslex.splashgallery.di.module

import dagger.Module

@Module(includes = [NetworkServiceModule::class, AppBindsModule::class])
class AppModule