package `in`.farmguide.myapplication.repository.network.model.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantsResponse (

    @SerializedName("restaurants")
    val restaurants: List<Restaurant>,
    @SerializedName("results_found")
    val resultsFound: Long,
    @SerializedName("results_shown")
    val resultsShown: Long,
    @SerializedName("results_start")
    val resultsStart: Long
)
