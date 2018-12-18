package `in`.farmguide.myapplication.domain

import `in`.farmguide.myapplication.data.ui.CategorizedRestaurants
import io.reactivex.Single

interface GetCategorizedRestaurantsUseCase {

    fun getRestaurants(): Single<List<CategorizedRestaurants>>
}