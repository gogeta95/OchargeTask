package `in`.farmguide.myapplication.dependency.module

import `in`.farmguide.myapplication.work.AppWorkManager
import `in`.farmguide.myapplication.work.AppWorkManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WorkModule {

    @Provides
    @Singleton
    fun provideWorkManager(appWorkManagerImpl: AppWorkManagerImpl): AppWorkManager = appWorkManagerImpl

}