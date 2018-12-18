package `in`.farmguide.myapplication.data.mapper

import `in`.farmguide.myapplication.repository.network.model.Post

fun List<Post>.mapToDb() =
    filter { it.id != null && it.userId != null }
        .map { `in`.farmguide.myapplication.repository.db.post.Post(it.id!!, it.userId!!, it.title, it.body) }
