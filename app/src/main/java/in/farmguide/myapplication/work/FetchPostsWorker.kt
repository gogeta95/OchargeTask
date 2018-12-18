package `in`.farmguide.myapplication.work

import `in`.farmguide.myapplication.MyApplication
import `in`.farmguide.myapplication.data.mapper.mapToDb
import `in`.farmguide.myapplication.repository.db.post.PostsRepository
import `in`.farmguide.myapplication.repository.network.ApiRepository
import android.content.Context
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchPostsWorker(context: Context, params: WorkerParameters) : RxWorker(context, params) {

    init {
        val appContext = applicationContext
        if (appContext is MyApplication){
            appContext.appComponent.inject(this)
        }
    }

    @Inject
    lateinit var apiRepository: ApiRepository
    @Inject
    lateinit var postsRepository: PostsRepository


    override fun createWork(): Single<Result> =
        apiRepository.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { it.mapToDb() }
            .observeOn(Schedulers.io())
            .flatMapCompletable { postsRepository.insertAll(it) }
            .toSingleDefault(Result.success())
            .onErrorReturn {
                if (it is HttpException) {
                    if (it.code() >= 500) Result.retry() else Result.failure()
                }
                Result.retry()
            }

}