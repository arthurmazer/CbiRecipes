package com.mazer.cbirecipes.presentation.ui.adapters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mazer.cbirecipes.databinding.ItemIngredientBinding
import com.mazer.cbirecipes.domain.entities.Ingredient

class IngredientsAdapter :  RecyclerView.Adapter<IngredientsViewHolder>() {

    private var ingredientList: List<Ingredient> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val binding = ItemIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.bind(ingredientList[position])
    }

    fun setList(list: List<Ingredient>) {
        this.ingredientList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =  ingredientList.size
}