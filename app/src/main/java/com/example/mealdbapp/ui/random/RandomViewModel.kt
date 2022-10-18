package com.example.mealdbapp.ui.random

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealdbapp.data.entities.meals.RandomMeal
import com.example.mealdbapp.repository.RandomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomViewModel @Inject constructor(val randomRepository: RandomRepository) : ViewModel() {

    val mealsLiveData = MutableLiveData<RandomMeal>()
    val errorLiveData = MutableLiveData<String>()

    fun fetchMeals() {
        viewModelScope.launch {
            val response = randomRepository.getAllRandomDetails()
            if (response.isSuccessful) {
                mealsLiveData.postValue(response.body())
                Log.i("datahere", response.body().toString())
            } else {
                errorLiveData.postValue(response.errorBody().toString())
                Log.i("errorhere", response.errorBody().toString())
            }
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}
