package com.mazer.cbirecipes.presentation.ui.adapters.home

import androidx.recyclerview.widget.RecyclerView
import com.mazer.cbirecipes.databinding.ItemCategoryBinding
import com.mazer.cbirecipes.domain.entities.Category

class CategoryViewHolder (private val binding: ItemCategoryBinding, private val onCategorySelected: (category: Category) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: Category) {
        binding.chipCategory.ivLogoUrl = category.urlImg
        binding.chipCategory.label = category.name

        binding.chipCategory.isChipEnabled = category.isSelected
        binding.chipCategory.invalidate()


        binding.root.setOnClickListener {
            if (!category.isSelected) {
                onCategorySelected.invoke(category)
            }
        }
    }
}

