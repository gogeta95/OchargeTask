package `in`.farmguide.myapplication.repository.db.post

import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(private val postsDao: PostsDao) : PostsRepository {

    override fun getPosts(): Flowable<List<PostMinimal>> = postsDao.getPosts()

    override fun insertAll(posts: List<Post>): Completable = Completable.fromAction{ postsDao.insertAll(posts) }
}