package `in`.farmguide.myapplication.ui.main

import `in`.farmguide.myapplication.R
import `in`.farmguide.myapplication.data.ui.CategorizedRestaurants
import `in`.farmguide.myapplication.data.ui.Resource
import `in`.farmguide.myapplication.domain.GetCategorizedRestaurantsUseCase
import `in`.farmguide.myapplication.ui.base.BaseViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class MainViewModel(private val getCategorizedRestaurantsUseCase: GetCategorizedRestaurantsUseCase) : BaseViewModel() {

    private val categoriesLiveData = MutableLiveData<Resource<List<CategorizedRestaurants>>>()

    fun getCategoriesObservable(): LiveData<Resource<List<CategorizedRestaurants>>> = categoriesLiveData

    init {
        getRestaurants()
    }

    fun onRetryClicked() = getRestaurants()

    private fun getRestaurants() {
        categoriesLiveData.postValue(Resource.Loading())

        addDisposable(
            getCategorizedRestaurantsUseCase.getRestaurants()
                .subscribe({
                    categoriesLiveData.postValue(Resource.Success(it))
                }, {
                    categoriesLiveData.postValue(Resource.Error(R.string.error_msg))
                })
        )
    }
}