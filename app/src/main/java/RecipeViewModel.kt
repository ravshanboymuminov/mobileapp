package com.example.firstprojwct

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList()) // List of all recipes
    val recipes: StateFlow<List<Recipe>> = _recipes

    private val _filteredRecipes = MutableStateFlow<List<Recipe>>(emptyList()) // List of filtered recipes
    val filteredRecipes: StateFlow<List<Recipe>> = _filteredRecipes

    private var lastQuery: String? = null

    private var job: Job? = null // To keep track of the coroutine job

    init {
        // Initialize with some sample data or fetch it
        _recipes.value = loadRecipes() // Sample function to load your list of recipes
        _filteredRecipes.value = _recipes.value
    }

    // Function to handle search query and filter the recipes
    fun searchRecipes(query: String) {
        if (query.length < 3) {
            _filteredRecipes.value = _recipes.value // Show all recipes if query is less than 3 characters
            lastQuery = null
            return
        }

        // Check if the current query is the same as the last query
        if (query == lastQuery) return

        lastQuery = query

        // Cancel any existing coroutine job
        job?.cancel()

        // Launch a new coroutine on the IO thread
        job = CoroutineScope(Dispatchers.IO).launch {
            // Perform the filtering operation on a background thread
            val filteredList = _recipes.value.filter {
                it.title.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
            }

            // Update the filtered list on the main thread
            _filteredRecipes.value = filteredList
        }
    }

    private fun loadRecipes(): List<Recipe> {
        // Replace this with your actual recipe loading logic
        return listOf(
            Recipe("Recipe 1", "Delicious description for recipe 1"),
            Recipe("Recipe 2", "Amazing description for recipe 2"),
            Recipe("Recipe 3", "Tasty recipe 3")
        )
    }

    // Don't forget to cancel the job if ViewModel is cleared
    override fun onCleared() {
        super.onCleared()
        job?.cancel() // Clean up the coroutine job when ViewModel is destroyed
    }
}
