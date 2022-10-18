package com.example.mealdbapp.data.entities.categories

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categories_Result")
data class Category(
    @PrimaryKey
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)
