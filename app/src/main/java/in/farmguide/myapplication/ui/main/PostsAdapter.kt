package `in`.farmguide.myapplication.ui.main

import `in`.farmguide.myapplication.R
import `in`.farmguide.myapplication.repository.db.post.PostMinimal
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.post_item.view.*
import javax.inject.Inject

class PostsAdapter @Inject constructor(private val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    var posts: List<PostMinimal> = emptyList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(PostDiffUtilCallback(field, value))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) =
        PostViewHolder(layoutInflater.inflate(R.layout.post_item, parent, false))


    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) =
        holder.bind(posts[position])

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(post: PostMinimal) =
            with(itemView) {
                tv_id.text = post.id.toString()
                tv_title.text = post.title
            }

    }

    private class PostDiffUtilCallback(private val oldList: List<PostMinimal>, private val newList: List<PostMinimal>) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

    }
}