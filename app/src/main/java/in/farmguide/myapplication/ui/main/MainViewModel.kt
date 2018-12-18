package `in`.farmguide.myapplication.ui.main

import `in`.farmguide.myapplication.R
import `in`.farmguide.myapplication.data.ui.CategorizedRestaurants
import `in`.farmguide.myapplication.data.ui.Resource
import `in`.farmguide.myapplication.domain.CityNotFoundException
import `in`.farmguide.myapplication.domain.GetCategorizedRestaurantsUseCase
import `in`.farmguide.myapplication.domain.SearchCityUseCase
import `in`.farmguide.myapplication.ui.base.BaseViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class MainViewModel(
    private val getCategorizedRestaurantsUseCase: GetCategorizedRestaurantsUseCase,
    private val searchCityUseCase: SearchCityUseCase
) : BaseViewModel() {

    private val categoriesLiveData = MutableLiveData<Resource<List<CategorizedRestaurants>>>()
    fun getCategoriesObservable(): LiveData<Resource<List<CategorizedRestaurants>>> = categoriesLiveData

    private val cityLiveData = MutableLiveData<String>()
    fun getCityLiveData(): LiveData<String> = cityLiveData


    init {
        getRestaurants(DEFAULT_CITY_ID)
    }


    private fun getRestaurants(cityId: Long) {
        categoriesLiveData.postValue(Resource.Loading())

        addDisposable(
            getCategorizedRestaurantsUseCase.getRestaurantsInCity(cityId)
                .subscribe({
                    categoriesLiveData.postValue(Resource.Success(it))
                }, {
                    it.printStackTrace()
                    categoriesLiveData.postValue(Resource.Error(R.string.error_msg))
                })
        )
    }

    fun onCityUpdated(city: String) {
        addDisposable(
            searchCityUseCase.getMostrelevantCity(city)
                .applyLoader()
                .subscribe({
                    cityLiveData.postValue(it.name)
                    getRestaurants(it.id)

                }, {
                    it.printStackTrace()
                    if (it is CityNotFoundException)
                        postError(R.string.city_not_found)
                })
        )
    }

    companion object {
        //delhi is 1
        private const val DEFAULT_CITY_ID = 1L
    }
}