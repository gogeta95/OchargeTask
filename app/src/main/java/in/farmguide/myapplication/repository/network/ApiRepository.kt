package `in`.farmguide.myapplication.repository.network

import `in`.farmguide.myapplication.repository.network.model.category.CategoriesResponse
import `in`.farmguide.myapplication.repository.network.model.restaurant.RestaurantsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRepository {

    @GET("categories")
    fun getCategories(): Single<CategoriesResponse>

    @GET("search?entity_type=city")
    fun getRestaurantInCategoryLocation(
        @Query("category") category: Int,
        @Query("entity_id") cityId: Int
    ): Single<RestaurantsResponse>

}