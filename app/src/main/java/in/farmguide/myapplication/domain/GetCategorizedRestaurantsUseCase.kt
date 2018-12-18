package `in`.farmguide.myapplication.domain

import `in`.farmguide.myapplication.data.ui.CategorizedRestaurants
import io.reactivex.Single

interface GetCategorizedRestaurantsUseCase {

    fun getRestaurantsInCity(cityId: Long): Single<List<CategorizedRestaurants>>
}