package com.mazer.cbirecipes.presentation.ui.adapters.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mazer.cbirecipes.databinding.ItemSearchBinding
import com.mazer.cbirecipes.domain.entities.RecipeMenu

class SearchAdapter (private val onRecipeSelected: (recipe: RecipeMenu) -> Unit)  :  RecyclerView.Adapter<SearchViewHolder>() {

    private var recipeList: ArrayList<RecipeMenu> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding, onRecipeSelected)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    fun setList(list: List<RecipeMenu>) {
        this.recipeList = ArrayList()
        this.recipeList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =  recipeList.size
}