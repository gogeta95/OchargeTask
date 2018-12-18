package `in`.farmguide.myapplication.dependency.module

import `in`.farmguide.myapplication.domain.GetPostsUseCase
import `in`.farmguide.myapplication.domainimpl.GetPostsUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideGetPostsUseCase(getPostsUseCaseImpl: GetPostsUseCaseImpl): GetPostsUseCase = getPostsUseCaseImpl

}