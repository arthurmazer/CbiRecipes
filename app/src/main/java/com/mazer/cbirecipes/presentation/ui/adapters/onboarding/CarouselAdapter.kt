package com.mazer.cbirecipes.presentation.ui.adapters.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mazer.cbirecipes.databinding.ItemCarouselBinding
import com.mazer.cbirecipes.domain.entities.Carousel

class CarouselAdapter(private val onBtnNexClicked: () -> Unit, private val onBtnEndClicked: () -> Unit) :  RecyclerView.Adapter<CarouselViewHolder>() {

    private var carouselList: List<Carousel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding, onBtnNexClicked, onBtnEndClicked)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(carouselList[position], carouselList.last() == carouselList[position])
    }

    fun setList(list: List<Carousel>) {
        this.carouselList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =  carouselList.size
}