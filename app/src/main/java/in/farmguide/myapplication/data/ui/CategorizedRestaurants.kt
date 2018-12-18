package `in`.farmguide.myapplication.data.ui

data class CategorizedRestaurants(
    val listType: ListType,
    val category: String,
    val categoryId: Int,
    val restaurants: List<Restaurant>
)

enum class ListType {
    GRID, CARD, HORIZONTAL
}