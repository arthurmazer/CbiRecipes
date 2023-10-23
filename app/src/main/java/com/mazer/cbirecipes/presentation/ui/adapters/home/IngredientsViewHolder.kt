package com.mazer.cbirecipes.presentation.ui.adapters.home

import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.mazer.cbirecipes.R
import com.mazer.cbirecipes.databinding.ItemIngredientBinding
import com.mazer.cbirecipes.domain.entities.Ingredient

class IngredientsViewHolder(private val binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(ingr: Ingredient) {
        val ingredientName = ingr.name
        val ingredient = binding.root.context.getString(R.string.ingredient_label, ingr.quantity, ingredientName)
        binding.tvIngredient.text = HtmlCompat.fromHtml(ingredient, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}
