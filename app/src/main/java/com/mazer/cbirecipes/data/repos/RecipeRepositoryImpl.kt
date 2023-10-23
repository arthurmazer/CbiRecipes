package com.mazer.cbirecipes.data.repos


import com.mazer.cbirecipes.data.remote.RemoteDataSource
import com.mazer.cbirecipes.domain.entities.Category
import com.mazer.cbirecipes.domain.entities.Recipe
import com.mazer.cbirecipes.domain.entities.RecipeMenu
import com.mazer.cbirecipes.data.remote.api.Result

class RecipeRepositoryImpl(private val remoteDataSource: RemoteDataSource): RecipeRepository {

    override suspend fun getAllCategoriesFromRecipe(): Result<List<Category>> {
        return remoteDataSource.getCategoriesFromRecipes()
    }

    override suspend fun getRecipesFromCategory(category: String?): Result<List<RecipeMenu>> {
        return remoteDataSource.getRecipesFromCategory(category)
    }

    override suspend fun getRecipesById(recipeId: Int): Result<Recipe> {
        return remoteDataSource.getRecipesById(recipeId)
    }

    override suspend fun getVideoUrl(firebaseReference: String): String {
        return remoteDataSource.getVideoUrl(firebaseReference)
    }

}