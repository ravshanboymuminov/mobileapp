package com.example.firstprojwct

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

// Adapter for handling list of recipes
class RecipeAdapter : ListAdapter<Recipe, RecipeAdapter.RecipeViewHolder>(RecipeDiffCallback()) {

    // Called when a new view holder is needed (to display a new item)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        // Inflate the view manually without ViewBinding
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    // Bind the data to the view holder (this happens for each item)
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position) // Get the recipe item at the current position
        holder.bind(recipe) // Bind the data to the views in the view holder
    }

    // ViewHolder to hold the views and bind data
    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

        fun bind(recipe: Recipe) {
            // Set the title and description from the recipe object
            titleTextView.text = recipe.title
            descriptionTextView.text = recipe.description
        }
    }

    // DiffUtil callback to compare old and new data for optimal performance
    class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
        // Check if the items are the same (based on title in this case)
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.title == newItem.title // This could be changed to a unique ID if available
        }

        // Check if the contents of the items are the same (title + description in this case)
        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem // Comparing the entire object (title + description)
        }
    }
}
