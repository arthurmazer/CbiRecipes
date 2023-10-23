package com.mazer.cbirecipes.domain.use_cases

import com.mazer.cbirecipes.data.remote.api.Result
import com.mazer.cbirecipes.data.repos.RecipeRepository
import com.mazer.cbirecipes.domain.entities.RecipeMenu

class SearchUseCase (private val recipeRepository: RecipeRepository) {

    suspend fun getAllRecipes() : Result<List<RecipeMenu>> {
        return recipeRepository.getRecipesFromCategory(null)
    }
}