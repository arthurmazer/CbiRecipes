package com.mazer.cbirecipes.presentation.ui.modules.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mazer.cbirecipes.domain.entities.Carousel
import com.mazer.cbirecipes.domain.use_cases.CheckFirstTimeUseCase
import com.mazer.cbirecipes.domain.use_cases.GetCarouselOnboardingUseCase
import com.mazer.cbirecipes.utils.extensions.asMutable
import com.mazer.cbirecipes.utils.extensions.liveData
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val getCarouselItemUseCase: GetCarouselOnboardingUseCase,
    private val checkFirstTimeUseCase: CheckFirstTimeUseCase
): ViewModel() {

    val carouselItems by liveData<List<Carousel>>()

    init {
        getOnboardingCarouselItem()
        setNotFirstTimeUser()
    }

    private fun getOnboardingCarouselItem(){
        carouselItems.asMutable()?.value = getCarouselItemUseCase.getCarouselItems()
    }

    private fun setNotFirstTimeUser(){
        viewModelScope.launch {
            checkFirstTimeUseCase.setFirstTimeUser()
        }
    }
}