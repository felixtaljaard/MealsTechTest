package com.example.mealdbapp.ui.search

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.mealdbapp.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModelSearch: SearchViewModel by viewModels()

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModelSearch.fetchSearches("Corba")
        viewModelSearch.searchLiveData.observe(viewLifecycleOwner) {

            binding!!.tvSearchName!!.text = it?.meals?.get(0)?.strMeal
            binding!!.tvSearchSumary!!.text = it?.meals?.get(0)?.strInstructions
            binding!!.tvSearchSumary!!.setMovementMethod(ScrollingMovementMethod())

            Glide.with(this)
                .load(it?.meals?.get(0)?.strMealThumb)
                .into(binding.imageSearch)
        }

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.buttonSearch?.setOnClickListener() {
            val value = binding!!.etSearch!!.text.toString()
            viewModelSearch!!.fetchSearches(value)

            viewModelSearch.searchLiveData.observe(viewLifecycleOwner) {
                binding!!.tvSearchName!!.text = it?.meals?.get(0)?.strMeal
                binding!!.tvSearchSumary!!.text = it?.meals?.get(0)?.strInstructions
                binding!!.tvSearchSumary!!.setMovementMethod(ScrollingMovementMethod())

                Glide.with(this)
                    .load(it?.meals?.get(0)?.strMealThumb)
                    .into(binding.imageSearch)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
