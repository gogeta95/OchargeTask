package `in`.farmguide.myapplication.ui.main

import `in`.farmguide.myapplication.R
import `in`.farmguide.myapplication.data.ui.CategorizedRestaurants
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import javax.inject.Inject

class CategoryAdapter @Inject constructor(private val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var categories: List<CategorizedRestaurants> = emptyList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(CategoryDiffUtilCallback(field, value))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) =
        CategoryViewHolder(layoutInflater.inflate(R.layout.category_item, parent, false))


    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bind(categories[position])

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(category: CategorizedRestaurants) =
            with(itemView) {

            }

    }

    private class CategoryDiffUtilCallback(
        private val oldList: List<CategorizedRestaurants>,
        private val newList: List<CategorizedRestaurants>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].categoryId == newList[newItemPosition].categoryId

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

    }
}