package com.mazer.cbirecipes.presentation.ui.adapters.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mazer.cbirecipes.databinding.ItemRecipeBinding
import com.mazer.cbirecipes.domain.entities.RecipeMenu

class RecipesViewHolder (private val binding: ItemRecipeBinding, private val onRecipeSelected: (recipe: RecipeMenu) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(recipe: RecipeMenu) {
        Glide.with(binding.root.context).load(recipe.urlThumb).into(binding.ivRecipe)
        binding.tvTitleRecipe.text = recipe.name
        binding.layoutDurationTime.tvTagTime.text = recipe.duration
        if (recipe.isVegan){
            binding.layoutIsVegan.root.visibility = View.VISIBLE
        }else{
            binding.layoutIsVegan.root.visibility = View.GONE
        }
        binding.root.setOnClickListener {
                onRecipeSelected.invoke(recipe)
        }
    }
}

