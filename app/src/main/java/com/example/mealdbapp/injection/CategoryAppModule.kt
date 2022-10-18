package com.example.mealdbapp.injection

import android.content.Context
import com.example.mealdbapp.data.local.CategoryAppDatabase
import com.example.mealdbapp.data.local.CategoryDAO
import com.example.mealdbapp.data.remote.CategoryRemoteDataSource
import com.example.mealdbapp.data.remote.CategoryService
import com.example.mealdbapp.repository.CategoryRepository
import com.example.mealdbapp.repository.RandomRepository
import com.example.mealdbapp.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryAppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideCategoryService(retrofit: Retrofit): CategoryService =
        retrofit.create(CategoryService::class.java)

    @Singleton
    @Provides
    fun provideCategoryRemoteDataSource(categoryService: CategoryService) =
        CategoryRemoteDataSource(categoryService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        CategoryAppDatabase.getCategoryDatabase(appContext)

    @Singleton
    @Provides
    fun provideCategoryDAO(appDatabase: CategoryAppDatabase) = appDatabase.categoryDAO()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: CategoryRemoteDataSource,
        localDataSource: CategoryDAO
    ) = CategoryRepository(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideRandomRepository(): RandomRepository =
        RandomRepository(provideCategoryService(provideRetrofit()))

    @Singleton
    @Provides
    fun provideSearchRepository(): SearchRepository =
        SearchRepository(provideCategoryService(provideRetrofit()))
}
