package com.mazer.cbirecipes.presentation.ui.adapters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mazer.cbirecipes.databinding.ItemCategoryBinding
import com.mazer.cbirecipes.domain.entities.Category

class CategoryAdapter (private val onCategorySelected: (category: Category) -> Unit)  :  RecyclerView.Adapter<CategoryViewHolder>() {

    private var categoryList: ArrayList<Category> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding, onCategorySelected)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    fun setList(list: List<Category>) {
        this.categoryList = ArrayList()
        this.categoryList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =  categoryList.size
}
