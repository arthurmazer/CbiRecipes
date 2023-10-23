package com.mazer.cbirecipes.presentation.ui.modules.recipe

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mazer.cbirecipes.data.remote.api.Result
import com.mazer.cbirecipes.domain.entities.Recipe
import com.mazer.cbirecipes.domain.use_cases.GetRecipeUseCases
import com.mazer.cbirecipes.domain.use_cases.PlayVideoUseCase
import com.mazer.cbirecipes.utils.constants.Constants.RECIPE_EXTRA
import com.mazer.cbirecipes.utils.extensions.asMutable
import com.mazer.cbirecipes.utils.extensions.liveData
import kotlinx.coroutines.launch

class RecipeViewModel (private val getReciepeUseCases: GetRecipeUseCases, private val playVideoUseCase: PlayVideoUseCase): ViewModel() {

    val recipe by liveData<Recipe>()
    val teaserUrl by liveData<String>()
    val errorMsg by liveData<String>()

    fun setExtras(bundle: Bundle){
        val recipeId = bundle.getInt(RECIPE_EXTRA)
        getRecipesFromCategory(recipeId)
    }

    private fun getRecipesFromCategory(recipeId: Int) {
        viewModelScope.launch {
            when (val result = getReciepeUseCases.getRecipeById(recipeId)) {
                is Result.Success -> {
                    recipe.asMutable()?.value = result.data
                    getVideoUrl(result.data.urlTeaserVideo)
                }
                is Result.Error -> {
                    val error = result.exception
                    errorMsg.asMutable()?.value = error.message ?: ""
                }
            }

        }
    }

    fun getVideoUrl(reference: String){
        viewModelScope.launch {
            teaserUrl.asMutable()?.value = playVideoUseCase.getVideoUrl(reference)
        }
    }
}