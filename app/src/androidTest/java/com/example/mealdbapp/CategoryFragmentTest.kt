package com.example.mealdbapp

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.mealdbapp.ui.category.CategoryFragment
import androidx.test.espresso.assertion.ViewAssertions.matches

import org.junit.Before
import org.junit.Test

class CategoryFragmentTest {
    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    @Before
    fun setUp() {
        launchFragmentInHiltContainer<CategoryFragment>(Bundle(), R.style.Theme_MealDbApp) {
            navController.setGraph(R.navigation.mobile_navigation)
            Navigation.setViewNavController(requireView(), navController)
        }
    }

    @Test
    fun checkIfRecyclerViewVisible() {
        onView(withId(R.id.recyclerViewCategory))
            .check(matches(isDisplayed()))
    }
}
