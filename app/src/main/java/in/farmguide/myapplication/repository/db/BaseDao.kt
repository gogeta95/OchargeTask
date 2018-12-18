package `in`.farmguide.myapplication.repository.db

import android.arch.persistence.room.Insert

abstract class BaseDao<T> {

    @Insert
    abstract fun insertAll(items: List<T>)

}