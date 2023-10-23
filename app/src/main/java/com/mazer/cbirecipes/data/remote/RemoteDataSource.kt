package com.mazer.cbirecipes.data.remote

import com.mazer.cbirecipes.domain.entities.Category
import com.mazer.cbirecipes.domain.entities.Recipe
import com.mazer.cbirecipes.domain.entities.RecipeMenu
import com.mazer.cbirecipes.data.remote.api.Result

interface RemoteDataSource {
    suspend fun getCategoriesFromRecipes(): Result<List<Category>>
    suspend fun getRecipesFromCategory(category: String?): Result<List<RecipeMenu>>
    suspend fun getRecipesById(recipeId: Int): Result<Recipe>
    suspend fun getVideoUrl(firebaseReference: String): String
}