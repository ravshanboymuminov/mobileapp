package com.example.firstprojwct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipeFragment : Fragment(R.layout.fragment_recipe) {

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView using findViewById
        recyclerView = view.findViewById(R.id.recyclerView)

        // Initialize the adapter
        recipeAdapter = RecipeAdapter()

        // Set LayoutManager and Adapter for RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recipeAdapter

        // Example data to be displayed in the RecyclerView
        val recipes = listOf(
            Recipe("Recipe 1", "Delicious description for recipe 1."),
            Recipe("Recipe 2", "Amazing description for recipe 2."),
            Recipe("Recipe 3", "Tasty recipe 3")
        )

        // Submit the list to the adapter
        recipeAdapter.submitList(recipes)
    }
}
