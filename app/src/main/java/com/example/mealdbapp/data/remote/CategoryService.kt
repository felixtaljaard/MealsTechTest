package com.example.mealdbapp.data.remote

import com.example.mealdbapp.data.entities.categories.CategoriesDetail
import com.example.mealdbapp.data.entities.categories.CategoriesResult
import com.example.mealdbapp.data.entities.meals.Meal
import com.example.mealdbapp.data.entities.meals.RandomMeal
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryService {
    @GET("categories.php")
    suspend fun getAllCategories(): Response<CategoriesResult>

    @GET("random.php")
    suspend fun getRandomDetails(): Response<RandomMeal>

    @GET("filter.php?c=Seafood")
    suspend fun getAllMeals(category: String): Response<CategoriesDetail>

    @GET("search.php")
    suspend fun getSearchDetails(@Query("s") category: String): Response<RandomMeal?>
}
