package com.mazer.cbirecipes.domain.use_cases

import com.mazer.cbirecipes.data.repos.RecipeRepository

class PlayVideoUseCase (private val recipeRepository: RecipeRepository) {

    suspend fun getVideoUrl(firebaseReference: String): String {
        return recipeRepository.getVideoUrl(firebaseReference)
    }
}