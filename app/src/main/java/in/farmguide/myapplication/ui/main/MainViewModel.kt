package `in`.farmguide.myapplication.ui.main

import `in`.farmguide.myapplication.R
import `in`.farmguide.myapplication.data.ui.Resource
import `in`.farmguide.myapplication.domain.GetPostsUseCase
import `in`.farmguide.myapplication.repository.db.post.PostMinimal
import `in`.farmguide.myapplication.ui.base.BaseViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable

class MainViewModel(private val getPostsUseCase: GetPostsUseCase) : BaseViewModel() {

    private val postsLiveData = MutableLiveData<Resource<List<PostMinimal>>>()
    private var disposable: Disposable? = null

    fun getPostsObservable(): LiveData<Resource<List<PostMinimal>>> = postsLiveData

    fun onLoadPostsClicked() {

        postsLiveData.postValue(Resource.Loading())

        disposable?.dispose()

        disposable = getPostsUseCase.getPosts()
            .onBackpressureLatest()
            .subscribe({
                postsLiveData.postValue(Resource.Success(it))
            }, {
                postsLiveData.postValue(Resource.Error(R.string.error_msg))
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

}