package com.example.mealdbapp.repository

import com.example.mealdbapp.data.remote.CategoryService
import javax.inject.Inject

class RandomRepository @Inject constructor(
    val categoryService: CategoryService
) {
    suspend fun getAllRandomDetails() =
        categoryService.getRandomDetails()
}
