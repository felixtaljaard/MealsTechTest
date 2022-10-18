package com.example.mealdbapp.ui.category

import androidx.lifecycle.ViewModel
import com.example.mealdbapp.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    repository: CategoryRepository
) : ViewModel() {
    val repository = repository.getCategories()
}
