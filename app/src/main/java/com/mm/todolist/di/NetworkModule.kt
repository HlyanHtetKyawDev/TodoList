package com.mm.todolist.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mm.todolist.BuildConfig
import com.mm.todolist.core.data.network.service.ApiService
import com.mm.todolist.list.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

    @Singleton
    @Provides
    fun provideGsonConverter() = GsonBuilder().setLenient().create()!!


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gSon: Gson): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gSon))
            .baseUrl(BuildConfig.BASE_URL).client(okHttpClient).build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext, AppDatabase::class.java, "todo-db"
    ).build()

    @Singleton
    @Provides
    fun provideDao(appDatabase: AppDatabase) = appDatabase.todoDao()

}