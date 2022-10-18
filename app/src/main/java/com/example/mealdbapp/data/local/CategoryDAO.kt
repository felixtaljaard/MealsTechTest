package com.example.mealdbapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mealdbapp.data.entities.categories.Category
import com.example.mealdbapp.data.entities.categories.MealDetail

@Dao
interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCategories(result: List<Category>)

    @Query("SELECT * FROM Categories_Result")
    fun getAllCategory(): LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMeals(meals: List<MealDetail>)

    @Query("SELECT * FROM MealDetail WHERE idMeal= :category")
    fun getMeal(category: String): LiveData<MealDetail>
}
