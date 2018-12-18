package `in`.farmguide.myapplication.dependency.module

import `in`.farmguide.myapplication.domain.GetCategorizedRestaurantsUseCase
import `in`.farmguide.myapplication.domainimpl.GetCategorizedRestaurantsUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideGetPostsUseCase(getPostsUseCaseImpl: GetCategorizedRestaurantsUseCaseImpl): GetCategorizedRestaurantsUseCase = getPostsUseCaseImpl

}