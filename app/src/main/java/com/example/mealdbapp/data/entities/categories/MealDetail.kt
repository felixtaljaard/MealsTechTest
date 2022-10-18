package com.example.mealdbapp.data.entities.categories

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MealDetail")
data class MealDetail(
    @PrimaryKey
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)