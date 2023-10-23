package com.mazer.cbirecipes.presentation.ui.adapters.onboarding

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mazer.cbirecipes.databinding.ItemCarouselBinding
import com.mazer.cbirecipes.domain.entities.Carousel

class CarouselViewHolder(private val binding: ItemCarouselBinding, private val onBtnNexClicked: () -> Unit, private val onBtnEndClicked: () -> Unit) : RecyclerView.ViewHolder(binding.root){

    fun bind(carousel: Carousel, isLast: Boolean) {
        binding.ivBackgroundImage.setImageDrawable(null)
        Glide.with(binding.root.context).load(carousel.imgId).into(binding.ivBackgroundImage)
        setZoomAnimation()
        binding.tvTitleCarousel.text = binding.root.context.getString(carousel.title)
        binding.tvSubtitleCarousel.text = binding.root.context.getString(carousel.subtitle)
        binding.tvTitleCarousel.startAnimation(setFadeAnimation(1500,0.0f,1.0f))
        binding.tvSubtitleCarousel.startAnimation(setFadeAnimation(3000,0.0f,1.0f))
        if (isLast) {
            binding.tvBtnNext.visibility = View.GONE
            binding.tvBtnEnd.visibility = View.VISIBLE
        } else {
            binding.tvBtnNext.visibility = View.VISIBLE
            binding.tvBtnEnd.visibility = View.GONE
        }

        binding.tvBtnNext.setOnClickListener {
            onBtnNexClicked.invoke()
        }

        binding.tvBtnEnd.setOnClickListener {
            onBtnEndClicked.invoke()
        }
    }

    private fun setFadeAnimation(duration: Long, fromAlpha: Float, toAlpha: Float): AlphaAnimation {
        val animation = AlphaAnimation(fromAlpha, toAlpha)
        animation.duration = duration
        return animation
        //binding.tvTitleCarousel.startAnimation(animation)
        //binding.tvSubtitleCarousel.startAnimation(animation)
    }

    private fun setZoomAnimation(){
        val fromX = 1.5f
        val toX = 1.0f
        val fromY = 1.5f
        val toY = 1.0f

        val duration = 13000L
        val scaleAnimation = ScaleAnimation(
            fromX, toX, fromY, toY,
            ScaleAnimation.RELATIVE_TO_SELF, 0.1f,
            ScaleAnimation.RELATIVE_TO_SELF, 0.1f
        )
        scaleAnimation.duration = duration
        binding.ivBackgroundImage.startAnimation(scaleAnimation)
    }
}