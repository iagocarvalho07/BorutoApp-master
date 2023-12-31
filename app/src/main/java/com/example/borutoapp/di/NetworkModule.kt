package com.example.borutoapp.di

import androidx.paging.ExperimentalPagingApi
import com.example.borutoapp.data.local.BorutoDATABASE
import com.example.borutoapp.data.remote.BorutoApi
import com.example.borutoapp.domain.repository.RemoteDataSorceImpl
import com.example.borutoapp.domain.repository.RemoteDataSource
import com.example.borutoapp.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHTPPClipet(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun BorutoApi(retrofit: Retrofit): BorutoApi {
        return retrofit.create(BorutoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSorce(
        borutoApi: BorutoApi,
        borutoDATABASE: BorutoDATABASE
    ): RemoteDataSource {
        return RemoteDataSorceImpl(borutoApi = borutoApi, borutoDATABASE = borutoDATABASE)
    }
}