package com.mazer.cbirecipes.presentation.ui.modules.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mazer.cbirecipes.databinding.ActivityMainBinding
import com.mazer.cbirecipes.domain.entities.Category
import com.mazer.cbirecipes.domain.entities.RecipeMenu
import com.mazer.cbirecipes.presentation.ui.adapters.home.CategoryAdapter
import com.mazer.cbirecipes.presentation.ui.adapters.home.RecipesAdapter
import com.mazer.cbirecipes.presentation.ui.modules.onboarding.OnboardingActivity
import com.mazer.cbirecipes.presentation.ui.modules.recipe.RecipeActivity
import com.mazer.cbirecipes.presentation.ui.modules.search.SearchActivity
import com.mazer.cbirecipes.utils.constants.Constants.RECIPE_EXTRA
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModel()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var recipeAdapter: RecipesAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        registerObservers()
    }

    private fun setupView() {
        setupCategoriesAdapter()
        setupSearchView()
        setupSwipeToRefresh()
    }

    private fun setupSwipeToRefresh() {
        binding.swipeToRefreshLayout.setOnRefreshListener {
            showLoadingRecipesShimmer(true)
            viewModel.reloadData()
        }
    }

    private fun setupSearchView() {
        binding.viewSearch.setOnClickListener {
            goToSearchActivity()
        }
    }

    private fun registerObservers() {
        viewModel.categoryList.observe(this){
            setupCategoryList(it)
            showLoadingAllDataShimmer(false)
        }

        viewModel.recipeList.observe(this){
            setupRecipeList(it)
            showLoadingRecipesShimmer(false)
        }

        viewModel.checkFirstTime.observe(this){

                goToOnboardingActivity()

        }

        viewModel.categoryName.observe(this){
            setupCategoryTitle(it)
        }

        viewModel.showNoDataPlaceholder.observe(this){
            if (it){
                binding.layoutError.root.visibility = View.VISIBLE
            }else{
                binding.layoutError.root.visibility = View.GONE
            }
        }
    }

    private fun setupCategoryTitle(category: String) {
        binding.tvLabelCategory.text = category
    }

    private fun setupCategoriesAdapter() {
        categoryAdapter = CategoryAdapter {
            viewModel.selectCategory(it)
            viewModel.getRecipesFromCategory(it.name)
            showLoadingRecipesShimmer(true)
        }
        recipeAdapter = RecipesAdapter {
            goToRecipeActivity(it.id)
        }
        binding.rvFilterRecipes.adapter = categoryAdapter
        binding.rvRecipes.adapter = recipeAdapter
    }

    private fun setupCategoryList(categoryList: List<Category>) {
        categoryAdapter.setList(categoryList)
    }

    private fun setupRecipeList(recipeList: List<RecipeMenu>) {
        recipeAdapter.setList(recipeList)
    }

    private fun goToRecipeActivity(recipeId: Int){
        val intent = Intent(this, RecipeActivity::class.java)
        intent.putExtra(RECIPE_EXTRA, recipeId)
        startActivity(intent)
    }

    private fun goToOnboardingActivity(){
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)
    }

    private fun goToSearchActivity(){
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }

    private fun showLoadingAllDataShimmer(showLoading: Boolean){
        if (showLoading){
            binding.shimmerViewLayout.startShimmer()
            binding.shimmerViewLayout.visibility = View.VISIBLE
        } else{
            binding.shimmerViewLayout.visibility = View.GONE
            binding.shimmerViewLayout.stopShimmer()
        }
        showData(showLoading)
    }

    private fun showData(isLoading: Boolean){
        binding.layoutCategoryAndRecipes.visibility = if (isLoading) View.GONE else View.VISIBLE
    }

    private fun showLoadingRecipesShimmer(showLoading: Boolean){
        if (showLoading){
            binding.shimmerViewLayoutRecipes.startShimmer()
            binding.shimmerViewLayoutRecipes.visibility = View.VISIBLE
        } else{
            binding.shimmerViewLayoutRecipes.visibility = View.GONE
            binding.shimmerViewLayoutRecipes.stopShimmer()
            binding.swipeToRefreshLayout.isRefreshing = false
        }
        showRecipesData(showLoading)
    }

    private fun showRecipesData(isLoading: Boolean){
        binding.rvRecipes.visibility = if (isLoading) View.GONE else View.VISIBLE
    }
}