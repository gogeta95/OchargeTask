package `in`.farmguide.myapplication.domainimpl

import `in`.farmguide.myapplication.data.ui.CategorizedRestaurants
import `in`.farmguide.myapplication.domain.GetCategorizedRestaurantsUseCase
import `in`.farmguide.myapplication.repository.network.ApiRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCategorizedRestaurantsUseCaseImpl @Inject constructor(
    private val apiRepository: ApiRepository) : GetCategorizedRestaurantsUseCase {

    override fun getRestaurants(): Single<List<CategorizedRestaurants>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}