package `in`.farmguide.myapplication.repository.db

import `in`.farmguide.myapplication.repository.db.post.Post
import `in`.farmguide.myapplication.repository.db.post.PostsDao
import android.app.Application
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Post::class], version = 1)
abstract class AppDb : RoomDatabase() {

    abstract fun postsDao(): PostsDao

    companion object {
        fun createInstance(application: Application): AppDb {
            return Room.databaseBuilder(application, AppDb::class.java, "app.db")
                .build()
        }

    }

}