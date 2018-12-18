package `in`.farmguide.myapplication.repository.db.post

import io.reactivex.Completable
import io.reactivex.Flowable

interface PostsRepository {

    fun getPosts(): Flowable<List<PostMinimal>>

    fun insertAll(posts: List<Post>) : Completable

}