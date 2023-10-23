package com.mazer.cbirecipes.presentation.ui.adapters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mazer.cbirecipes.databinding.ItemTagIngredientBinding
import com.mazer.cbirecipes.domain.entities.Ingredient

class TagAdapter :  RecyclerView.Adapter<TagViewHolder>() {

    private var tagList: List<Ingredient> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding = ItemTagIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(tagList[position])
    }

    fun setList(list: List<Ingredient>) {
        this.tagList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =  tagList.size
}