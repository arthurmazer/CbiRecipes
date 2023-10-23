package com.mazer.cbirecipes.presentation.ui.adapters.home

import androidx.recyclerview.widget.RecyclerView
import com.mazer.cbirecipes.databinding.ItemTagIngredientBinding
import com.mazer.cbirecipes.domain.entities.Ingredient

class TagViewHolder (private val binding: ItemTagIngredientBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(ingredient: Ingredient) {
        binding.tvTag.text = ingredient.name
    }
}
