package com.example.mealdbapp.data.entities.meals

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Meals_DB1")
data class Meal(
    @PrimaryKey
    val idMeal: String,
    val strArea: String,
    val strCategory: String,
    val strInstructions: String,
    val strMeal: String,
    val strMealThumb: String,
)
