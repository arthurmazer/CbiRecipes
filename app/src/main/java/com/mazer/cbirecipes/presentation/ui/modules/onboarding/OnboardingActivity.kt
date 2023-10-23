package com.mazer.cbirecipes.presentation.ui.modules.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.mazer.cbirecipes.databinding.ActivityOnboardingBinding
import com.mazer.cbirecipes.domain.entities.Carousel
import com.mazer.cbirecipes.presentation.ui.adapters.onboarding.CarouselAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private val viewModel : OnboardingViewModel by viewModel()
    private lateinit var adapter: CarouselAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        registerObservers()
    }

    private fun setupView() {
        setupCarouselRecyclerView()
    }

    private fun setupCarouselRecyclerView() {
        adapter = CarouselAdapter({
            goToNextSlide()
        }, {
            finish()
        })
        binding.rvCarousel.adapter = adapter
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvCarousel)
    }

    private fun goToNextSlide() {
        binding.rvCarousel.smoothScrollToPosition(
            (binding.rvCarousel.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() + 1
        )
    }

    private fun registerObservers() {
        viewModel.carouselItems.observe(this){
            setupCarouseList(it)
        }
    }


    private fun setupCarouseList(it: List<Carousel>) {
        adapter.setList(it)
    }
}