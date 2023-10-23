package com.mazer.cbirecipes.presentation.ui.modules.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mazer.cbirecipes.domain.entities.Category
import com.mazer.cbirecipes.domain.entities.RecipeMenu
import com.mazer.cbirecipes.domain.use_cases.CheckFirstTimeUseCase
import com.mazer.cbirecipes.domain.use_cases.GetRecipeUseCases
import com.mazer.cbirecipes.utils.extensions.asMutable
import com.mazer.cbirecipes.utils.extensions.liveData
import kotlinx.coroutines.launch
import com.mazer.cbirecipes.data.remote.api.Result

class MainViewModel(private val getRecipeUseCases: GetRecipeUseCases,
                    private val checkFirstTimeUseCase: CheckFirstTimeUseCase): ViewModel() {

    val categoryList by liveData<List<Category>>()
    val recipeList by liveData<List<RecipeMenu>>()
    val checkFirstTime by liveData<Boolean>()
    val categoryName by liveData<String>()
    val showNoDataPlaceholder by liveData<Boolean>()
    val errorMsg by liveData<String>()

    init {
        checkIsFirstTimeUser()
        loadAllCategoriesFromRecipe()

    }

    private fun checkIsFirstTimeUser(){
        checkFirstTime.asMutable()?.value = checkFirstTimeUseCase.isFirstTimeUser()
    }

    private fun loadAllCategoriesFromRecipe() {
        viewModelScope.launch {
            when (val result = getRecipeUseCases.getAllCategoriesFromRecipe()){
                is Result.Success -> {
                    categoryList.asMutable()?.value = result.data
                    val firstCategory = result.data.firstOrNull()
                    categoryName.asMutable()?.value = firstCategory?.name
                    getRecipesFromCategory(firstCategory?.name ?: return@launch)
                }
                is Result.Error -> {
                    val error = result.exception
                    errorMsg.asMutable()?.value = error.message ?: ""
                }
            }

        }
    }

    fun reloadData(){
        val categorySelected = categoryList.value?.filter { it.isSelected }?.get(0)
        getRecipesFromCategory(categorySelected?.name ?: return)
    }


    fun getRecipesFromCategory(category: String) {
        viewModelScope.launch {
            when (val result = getRecipeUseCases.getRecipesFromCategory(category)){
                is Result.Success -> {
                    recipeList.asMutable()?.value = result.data
                    showNoDataPlaceholder.asMutable()?.value = result.data.isEmpty()
                }
                is Result.Error -> {
                    val error = result.exception
                    errorMsg.asMutable()?.value = error.message ?: ""
                }
            }



        }
    }

    fun selectCategory(category: Category) {
        val newCategoryList = arrayListOf<Category>()
        val currentCategoryList = categoryList.value.orEmpty().toMutableList()
        currentCategoryList.forEach {
            if (it.name == category.name) {
                newCategoryList.add(Category(it.name, it.urlImg, true))
                categoryName.asMutable()?.value = it.name
            } else {
                newCategoryList.add(Category(it.name, it.urlImg, false))
            }
        }
        categoryList.asMutable()?.value = newCategoryList
    }
}