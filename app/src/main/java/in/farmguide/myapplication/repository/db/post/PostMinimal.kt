package `in`.farmguide.myapplication.repository.db.post

import android.arch.persistence.room.ColumnInfo

data class PostMinimal(
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String?
)