package com.mazer.cbirecipes.presentation.ui.adapters.search

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mazer.cbirecipes.databinding.ItemSearchBinding
import com.mazer.cbirecipes.domain.entities.RecipeMenu

class SearchViewHolder (private val binding: ItemSearchBinding, private val onRecipeSelected: (recipe: RecipeMenu) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(recipe: RecipeMenu) {
        Glide.with(binding.root.context).load(recipe.urlThumb).into(binding.ivRecipeImg)
        binding.tvTitleRecipe.text = recipe.name
        binding.tvDuration.text = recipe.duration
        if (recipe.isVegan){
            binding.LayoutVegan.root.visibility = View.VISIBLE
        }else{
            binding.LayoutVegan.root.visibility = View.GONE
        }
    }
}
