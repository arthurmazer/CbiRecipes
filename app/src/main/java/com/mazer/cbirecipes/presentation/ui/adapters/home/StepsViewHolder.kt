package com.mazer.cbirecipes.presentation.ui.adapters.home

import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.mazer.cbirecipes.R
import com.mazer.cbirecipes.databinding.ItemStepBinding
import com.mazer.cbirecipes.domain.entities.Steps

class StepsViewHolder (private val binding: ItemStepBinding, private val onVideoPlayed: (videoUrl: String) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(steps: Steps) {
        val title = binding.root.context.getString(R.string.step_label, steps.stepIndex.toString(), steps.name)
        binding.tvStepTitle.text = HtmlCompat.fromHtml(title, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.tvStepDescription.text = steps.description
        //TODO deal with Recycler View?
        binding.layoutPlayVideo.root.setOnClickListener {
            onVideoPlayed.invoke(steps.urlVideo)
        }
    }
}
