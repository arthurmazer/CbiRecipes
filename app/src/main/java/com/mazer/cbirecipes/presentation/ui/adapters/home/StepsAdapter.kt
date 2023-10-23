package com.mazer.cbirecipes.presentation.ui.adapters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mazer.cbirecipes.databinding.ItemStepBinding
import com.mazer.cbirecipes.domain.entities.Category
import com.mazer.cbirecipes.domain.entities.Steps

class StepsAdapter  (private val onVideoPlayed: (videoUrl: String) -> Unit):  RecyclerView.Adapter<StepsViewHolder>() {

    private var stepsList: List<Steps> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        val binding = ItemStepBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StepsViewHolder(binding, onVideoPlayed)
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        holder.bind(stepsList[position])
    }

    fun setList(list: List<Steps>) {
        this.stepsList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =  stepsList.size
}