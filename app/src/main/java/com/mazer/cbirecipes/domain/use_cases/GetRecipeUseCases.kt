package com.mazer.cbirecipes.domain.use_cases

import com.mazer.cbirecipes.data.repos.RecipeRepository
import com.mazer.cbirecipes.domain.entities.Category
import com.mazer.cbirecipes.domain.entities.Recipe
import com.mazer.cbirecipes.domain.entities.RecipeMenu
import com.mazer.cbirecipes.data.remote.api.Result

class GetRecipeUseCases(private val recipeRepository: RecipeRepository) {

    suspend fun getAllCategoriesFromRecipe() : Result<List<Category>> {
        return recipeRepository.getAllCategoriesFromRecipe()
    }

    suspend fun getRecipesFromCategory(category: String) : Result<List<RecipeMenu>> {
        return recipeRepository.getRecipesFromCategory(category)
    }

    suspend fun getRecipeById(recipeId: Int) : Result<Recipe> {
        return recipeRepository.getRecipesById(recipeId)
    }
}