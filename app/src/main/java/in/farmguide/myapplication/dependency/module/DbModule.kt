package `in`.farmguide.myapplication.dependency.module

import `in`.farmguide.myapplication.repository.db.AppDb
import `in`.farmguide.myapplication.repository.db.post.PostsRepository
import `in`.farmguide.myapplication.repository.db.post.PostsRepositoryImpl
import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    internal fun provideAppDb(application: Application) = AppDb.createInstance(application)

    @Provides
    @Singleton
    internal fun providePostDao(appDb: AppDb) = appDb.postsDao()

    @Provides
    @Singleton
    internal fun providePostRepository(policyCropRepositoryImpl: PostsRepositoryImpl): PostsRepository =
        policyCropRepositoryImpl
}