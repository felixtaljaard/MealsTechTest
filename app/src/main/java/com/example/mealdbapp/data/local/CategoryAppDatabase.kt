package com.example.mealdbapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mealdbapp.data.entities.categories.Category
import com.example.mealdbapp.data.entities.meals.Meal

@Database(entities = [Category::class, Meal::class, com.example.mealdbapp.data.entities.categories.MealDetail::class], version = 6, exportSchema = false)
abstract class CategoryAppDatabase : RoomDatabase() {
    abstract fun categoryDAO(): CategoryDAO

    companion object {
        @Volatile
        private var INSTANCE: CategoryAppDatabase? = null

        fun getCategoryDatabase(context: Context): CategoryAppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, CategoryAppDatabase::class.java, "MealDBCategory5")
                .fallbackToDestructiveMigration()
                .build()
    }
}
