package com.example.mealdbapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealdbapp.data.entities.meals.RandomMeal
import com.example.mealdbapp.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val searchRepository: SearchRepository) : ViewModel() {

    val searchLiveData = MutableLiveData<RandomMeal?>()
    val errorLiveData = MutableLiveData<String>()

    fun fetchSearches(userRequest: String) {
        viewModelScope.launch {
            var response = searchRepository.getAllSearchDetails(userRequest)
            if (response.isSuccessful) {
                searchLiveData.postValue(response.body())
            } else {
                errorLiveData.postValue(response.errorBody().toString())
            }
        }
    }
}
