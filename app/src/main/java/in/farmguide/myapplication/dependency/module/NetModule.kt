package `in`.farmguide.myapplication.dependency.module

import `in`.farmguide.myapplication.repository.network.ApiRepository
import `in`.farmguide.myapplication.BuildConfig
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule {


    @Provides
    @Singleton
    internal fun provideGson() = Gson()

    @Provides
    @Singleton
    internal fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return loggingInterceptor
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor)
        }
        builder.connectTimeout(10, TimeUnit.SECONDS)
        return builder.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(client: OkHttpClient, gson: Gson) = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiRepository = retrofit.create(
        ApiRepository::class.java)


}