package `in`.farmguide.myapplication.domain

import `in`.farmguide.myapplication.repository.db.post.PostMinimal
import io.reactivex.Flowable

interface GetPostsUseCase {

    fun getPosts(): Flowable<List<PostMinimal>>
}