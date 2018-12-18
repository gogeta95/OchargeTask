package `in`.farmguide.myapplication.repository.network

import `in`.farmguide.myapplication.repository.network.model.Post
import io.reactivex.Single
import retrofit2.http.GET

interface ApiRepository {

    @GET("posts")
    fun getPosts(): Single<List<Post>>

}