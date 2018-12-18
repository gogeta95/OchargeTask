package `in`.farmguide.myapplication.ui.main

import `in`.farmguide.myapplication.R
import `in`.farmguide.myapplication.extensions.loadImage
import `in`.farmguide.myapplication.repository.network.model.restaurant.Restaurant
import android.graphics.Color
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.restaurant_item_base.view.*
import timber.log.Timber

class RestaurantAdapter(private val layoutInflater: LayoutInflater, private val type: Int) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    var restaurants: List<Restaurant> = emptyList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(RestaurantDiffUtilCallback(field, value))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RestaurantViewHolder(
            layoutInflater.inflate(
                if (type == CategoryAdapter.VIEW_TYPE_LIST) R.layout.restaurant_item_normal
                else R.layout.restaurant_item_card,
                parent,
                false
            )
        )

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) =
        holder.bind(restaurants[position])

    private class RestaurantDiffUtilCallback(
        private val oldList: List<Restaurant>,
        private val newList: List<Restaurant>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

    }


    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(restaurant: Restaurant) {
            with(itemView) {
                rest_image.loadImage(restaurant.thumb)
                tv_rest_name.text = restaurant.name
                tv_cuisines.text = restaurant.cuisines
                tv_rating.text = restaurant.userRating?.aggregateRating
                val ratingColor = restaurant.userRating?.ratingColor

                tv_rating.setBackgroundColor(
                    if (ratingColor.isNullOrEmpty())
                        Color.TRANSPARENT
                    else
                        Color.parseColor("#$ratingColor")
                )

            }
        }

    }
}