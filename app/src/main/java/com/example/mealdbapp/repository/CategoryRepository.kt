package com.example.mealdbapp.repository

import com.example.mealdbapp.data.local.CategoryDAO
import com.example.mealdbapp.data.remote.CategoryRemoteDataSource
import com.example.mealdbapp.utils.performGetOperation
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val remoteDataSource: CategoryRemoteDataSource,
    private val localDataSource: CategoryDAO
) {
    fun getCategories() = performGetOperation(
        databaseQuery = { localDataSource.getAllCategory() },
        networkCall = { remoteDataSource.getCategories() },
        saveCallResult = { localDataSource.insertAllCategories(it.categories) }
    )
}
