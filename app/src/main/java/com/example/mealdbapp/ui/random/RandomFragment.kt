package com.example.mealdbapp.ui.random

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.mealdbapp.databinding.FragmentRandomBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomFragment : Fragment() {
    private val randomViewModel: RandomViewModel by viewModels()

    private var _binding: FragmentRandomBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonRandom.setOnClickListener() {
            randomViewModel.fetchMeals()
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        randomViewModel.fetchMeals()
        randomViewModel.mealsLiveData.observe(viewLifecycleOwner) {
            Log.i("RandomMeal", "" + it.meals.get(0).strMeal)
            binding.tvRandName.text = it.meals.get(0).strMeal
            binding.tvRandSummary.text = it.meals.get(0).strInstructions
            binding.tvRandSummary.setMovementMethod(ScrollingMovementMethod())
            Glide.with(this)
                .load(it.meals.get(0).strMealThumb)
                .into(binding.imageViewRand)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
