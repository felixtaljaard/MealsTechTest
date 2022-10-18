package com.example.mealdbapp.repository

import com.example.mealdbapp.data.remote.CategoryService
import javax.inject.Inject

class SearchRepository @Inject constructor(val categoryService: CategoryService) {
    suspend fun getAllSearchDetails(category: String) =
        categoryService.getSearchDetails(category)
}
