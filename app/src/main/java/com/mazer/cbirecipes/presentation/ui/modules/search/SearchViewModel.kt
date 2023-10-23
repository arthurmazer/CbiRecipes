package com.mazer.cbirecipes.presentation.ui.modules.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mazer.cbirecipes.data.remote.api.Result
import com.mazer.cbirecipes.domain.entities.RecipeMenu
import com.mazer.cbirecipes.domain.use_cases.GetCarouselOnboardingUseCase
import com.mazer.cbirecipes.domain.use_cases.SearchUseCase
import com.mazer.cbirecipes.utils.extensions.asMutable
import com.mazer.cbirecipes.utils.extensions.liveData
import kotlinx.coroutines.launch

class SearchViewModel(private val searchUseCase: SearchUseCase): ViewModel() {

    val recipeList by liveData<List<RecipeMenu>>()

    init {
        getAllRecipes()
    }

    fun getAllRecipes(){
        viewModelScope.launch {
            when (val result = searchUseCase.getAllRecipes()){
                is Result.Success -> {
                    recipeList.asMutable()?.value = result.data
                }
                is Result.Error -> {
                    val error = result.exception
                }
            }

        }
    }
}