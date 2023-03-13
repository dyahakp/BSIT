package com.dyah.bsit.di

import androidx.viewbinding.BuildConfig
import com.dyah.bsit.domain.Service
import com.dyah.bsit.domain.RemoteDataSource
import com.dyah.bsit.domain.RemoteDataSourceImpl
import com.dyah.bsit.domain.repository.Repository
import com.dyah.bsit.domain.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providerOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://private-54eacf-fazztrack.apiary-mock.com/")
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit : Retrofit) : Service = retrofit.create(Service::class.java)

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RemoteDataSource): Repository =
        RepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun provideRemoteDataSource(service: Service): RemoteDataSource =
        RemoteDataSourceImpl(service)

}