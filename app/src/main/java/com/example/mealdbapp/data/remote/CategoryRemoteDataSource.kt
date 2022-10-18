package com.example.mealdbapp.data.remote

import javax.inject.Inject

class CategoryRemoteDataSource @Inject constructor(
    private val categoryService: CategoryService
) : BaseDataSource() {
    suspend fun getCategories() = getResult { categoryService.getAllCategories() }
    suspend fun getMeals(category: String) = getResult { categoryService.getAllMeals(category) }
}
