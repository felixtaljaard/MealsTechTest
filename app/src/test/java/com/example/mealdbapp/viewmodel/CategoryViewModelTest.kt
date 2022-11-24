package com.example.mealdbapp.viewmodel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.mealdbapp.data.entities.categories.Category
import com.example.mealdbapp.repository.CategoryRepository
import com.example.mealdbapp.ui.category.CategoryViewModel
import com.example.mealdbapp.utils.Resource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CategoryViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private  lateinit var viewModel: CategoryViewModel
    private val categoriesList = listOf(
        Category("101", "fries", "", ""),
        Category("102", "korean", "", "")
    )
    private val repository: CategoryRepository = mockk(relaxed = true){
        every { getCategories() } returns MutableLiveData(Resource.success(categoriesList))
    }

    private val categoriesObserver: Observer<Resource<List<Category>>> = mockk(relaxed = true)

    @Before
    fun setUp(){
        viewModel= CategoryViewModel(repository)
        viewModel.repository.observeForever(categoriesObserver)
    }

    @Test
    fun `get categories should return emit list of characters`(){
        verify { categoriesObserver.onChanged(Resource.success(categoriesList)) }
        assert(viewModel.repository.value == Resource.success(categoriesList))
    }
}