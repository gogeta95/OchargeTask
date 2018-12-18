package `in`.farmguide.myapplication.dependency.component

import `in`.farmguide.myapplication.MyApplication
import `in`.farmguide.myapplication.dependency.builder.ActivityBuilder
import `in`.farmguide.myapplication.dependency.module.DbModule
import `in`.farmguide.myapplication.dependency.module.DomainModule
import `in`.farmguide.myapplication.dependency.module.NetModule
import `in`.farmguide.myapplication.dependency.module.WorkModule
import `in`.farmguide.myapplication.work.FetchPostsWorker
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules =
    [AndroidInjectionModule::class,NetModule::class, DomainModule::class, DbModule::class,
        ActivityBuilder::class, WorkModule::class]
)
interface AppComponent {

    fun inject(application: MyApplication)

    fun inject(fetchPostsWorker: FetchPostsWorker)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun app(application: Application): Builder

        fun build(): AppComponent

    }

}