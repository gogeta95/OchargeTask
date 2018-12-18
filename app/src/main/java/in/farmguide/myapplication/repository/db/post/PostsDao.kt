package `in`.farmguide.myapplication.repository.db.post

import `in`.farmguide.myapplication.repository.db.BaseDao
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
abstract class PostsDao : BaseDao<Post>() {

    @Query("select id,title from posts")
    abstract fun getPosts(): Flowable<List<PostMinimal>>

}