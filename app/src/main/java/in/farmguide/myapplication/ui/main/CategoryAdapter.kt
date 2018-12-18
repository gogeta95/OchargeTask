package `in`.farmguide.myapplication.ui.main

import `in`.farmguide.myapplication.R
import `in`.farmguide.myapplication.data.ui.CategorizedRestaurants
import android.support.v7.util.DiffUtil
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.category_item.view.*
import javax.inject.Inject

class CategoryAdapter @Inject constructor(private val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var categories: List<CategorizedRestaurants> = emptyList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(CategoryDiffUtilCallback(field, value))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryViewHolder(layoutInflater.inflate(R.layout.category_item, parent, false), viewType)


    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bind(categories[position])

    override fun getItemViewType(position: Int) =
        when (position % 3) {
            0 -> VIEW_TYPE_CARDS
            1 -> VIEW_TYPE_GRID
            else -> VIEW_TYPE_LIST
        }

    inner class CategoryViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {

        private val restaurantAdapter: RestaurantAdapter = RestaurantAdapter(layoutInflater, viewType)

        init {

            with(itemView) {
                recycler_view.layoutManager =
                        if (viewType == VIEW_TYPE_LIST || viewType == VIEW_TYPE_CARDS)
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        else
                            GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
                recycler_view.adapter = restaurantAdapter
            }
        }

        fun bind(category: CategorizedRestaurants) =
            with(itemView) {
                tv_category.text = category.category
                restaurantAdapter.restaurants = category.restaurants
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

    companion object {
        const val VIEW_TYPE_GRID = 1
        const val VIEW_TYPE_LIST = 2
        const val VIEW_TYPE_CARDS = 3
    }
}