package com.mazer.cbirecipes.domain.use_cases

import com.mazer.cbirecipes.R
import com.mazer.cbirecipes.domain.entities.Carousel

class GetCarouselOnboardingUseCase {

    fun getCarouselItems(): List<Carousel>{
        val slide1 = Carousel(
            R.drawable.slide3,
            R.string.slide1_title,
            R.string.slide1_text
        )

        val slide2 = Carousel(
            R.drawable.slide2,
            R.string.slide2_title,
            R.string.slide2_text
        )

        val slide3 = Carousel(
            R.drawable.slide1,
            R.string.slide3_title,
            R.string.slide3_text
        )

        return listOf(slide1,slide2,slide3)
    }
}