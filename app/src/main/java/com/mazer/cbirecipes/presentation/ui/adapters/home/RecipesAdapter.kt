package com.mazer.cbirecipes.presentation.ui.adapters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mazer.cbirecipes.databinding.ItemRecipeBinding
import com.mazer.cbirecipes.domain.entities.RecipeMenu

class RecipesAdapter  (private val onRecipeSelected: (category: RecipeMenu) -> Unit)  :  RecyclerView.Adapter<RecipesViewHolder>() {

    private var recipeListList: ArrayList<RecipeMenu> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipesViewHolder(binding, onRecipeSelected)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(recipeListList[position])
    }

    fun setList(list: List<RecipeMenu>) {
        this.recipeListList = ArrayList()
        this.recipeListList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =  recipeListList.size
}
