package com.mazer.cbirecipes.data.remote.api

import com.mazer.cbirecipes.domain.entities.Category
import com.mazer.cbirecipes.domain.entities.Recipe
import com.mazer.cbirecipes.domain.entities.RecipeMenu
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("get_category_recipes/")
    suspend fun getCategoriesFromRecipe(): Response<List<Category>>

    @GET("get_recipes_from_category/")
    suspend fun getRecipesFromCategory(@Query("category") category: String? = null): Response<List<RecipeMenu>>

    @GET("get_recipes_by_id/")
    suspend fun getRecipesById(@Query("recipeId") recipeId: Int): Response<Recipe>
}