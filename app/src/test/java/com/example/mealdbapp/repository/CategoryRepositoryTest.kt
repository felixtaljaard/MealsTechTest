package com.example.mealdbapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mealdbapp.data.entities.categories.Category
import com.example.mealdbapp.data.entities.categories.MealDetail
import com.example.mealdbapp.data.local.CategoryDAO
import com.example.mealdbapp.data.remote.CategoryRemoteDataSource
import com.example.mealdbapp.utils.Resource
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CategoryRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val charactersObserver: Observer<Resource<List<Category>>> = mockk(relaxed = true)
    private val charactersObserverDetail: Observer<Resource<MealDetail>> = mockk(relaxed = true)
    private val remoteDataSource: CategoryRemoteDataSource = mockk(relaxed = true)
    private val localDataSource : CategoryDAO = mockk(relaxed = true)
    private lateinit var repository: CategoryRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp(){
        repository = CategoryRepository(remoteDataSource, localDataSource)
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `get category should return livedata of category API Data`(){
       repository.getCategories().observeForever(charactersObserver)
       verify { charactersObserver.onChanged(any()) }
    }
}