package com.mazer.cbirecipes.data.remote

import com.google.firebase.storage.FirebaseStorage
import com.mazer.cbirecipes.data.remote.api.ApiService
import com.mazer.cbirecipes.data.remote.api.Result
import com.mazer.cbirecipes.domain.entities.Category
import com.mazer.cbirecipes.domain.entities.Recipe
import com.mazer.cbirecipes.domain.entities.RecipeMenu
import kotlinx.coroutines.tasks.await

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {
    override suspend fun getCategoriesFromRecipes(): Result<List<Category>> {
        return try {
            val response = apiService.getCategoriesFromRecipe()
            if (response.isSuccessful) {
                Result.Success(formatResponseCategories(response.body()))
            } else {
                Result.Error(Exception("Erro ao comunicar com o servidor, tente novamente mais tarde!"))
            }
        }catch(ex: Exception){
            Result.Error(Exception("Erro ao comunicar com o servidor, tente novamente mais tarde!"))
        }
    }

    override suspend fun getRecipesFromCategory(category: String?): Result<List<RecipeMenu>> {
        return try {
            val response = apiService.getRecipesFromCategory(category)
            if (response.isSuccessful) {
                Result.Success(response.body() ?: emptyList())
            } else {
                Result.Error(Exception("Erro ao comunicar com o servidor, tente novamente mais tarde!"))
            }
        }catch(ex: Exception){
            Result.Error(Exception("Erro ao comunicar com o servidor, tente novamente mais tarde!"))
        }

    }

    override suspend fun getRecipesById(recipeId: Int): Result<Recipe> {
        return try {
            val response = apiService.getRecipesById(recipeId)
            if (response.isSuccessful) {
                Result.Success(response.body() ?: throw Exception("Receita não encontrada!"))
            } else {
                Result.Error(Exception("Erro ao comunicar com o servidor, tente novamente mais tarde!"))
            }
        } catch(ex: Exception){
            Result.Error(Exception("Erro ao comunicar com o servidor, tente novamente mais tarde!"))
        }

    }

    override suspend fun getVideoUrl(firebaseReference: String): String {
        val storageReference = FirebaseStorage.getInstance().getReference(firebaseReference)
        return storageReference.downloadUrl.await().toString()
    }

    private fun formatResponseCategories(categoriesList: List<Category>?): List<Category>{
        categoriesList?.first()?.isSelected = true
        return categoriesList ?: emptyList()
    }
}