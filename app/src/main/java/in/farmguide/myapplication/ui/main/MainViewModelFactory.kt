package `in`.farmguide.myapplication.ui.main

import `in`.farmguide.myapplication.domain.GetPostsUseCase
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModelFactory @Inject constructor(private val getPostsUseCase: GetPostsUseCase) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getPostsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}