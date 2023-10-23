package com.mazer.cbirecipes.presentation.ui.modules.search

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.mazer.cbirecipes.databinding.ActivitySearchBinding
import com.mazer.cbirecipes.domain.entities.Category
import com.mazer.cbirecipes.domain.entities.RecipeMenu
import com.mazer.cbirecipes.presentation.ui.adapters.search.SearchAdapter
import com.mazer.cbirecipes.presentation.ui.modules.home.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel : SearchViewModel by viewModel()
    private lateinit var searchAdapter: SearchAdapter

            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        registerObservers()
    }

    private fun registerObservers() {
        viewModel.recipeList.observe(this){
            setupSearchList(it)
        }
    }

    private fun setupView() {
        initSearchWithFocus()
        setupBackButton()
        setupAdapter()
    }

    private fun setupAdapter() {
        searchAdapter = SearchAdapter {

        }
        binding.rvSearch.adapter = searchAdapter
    }

    private fun setupSearchList(recipeList: List<RecipeMenu>) {
        searchAdapter.setList(recipeList)
    }

    private fun setupBackButton() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initSearchWithFocus() {
        binding.etSearch.requestFocus()
        showKeyboard()
    }

    private fun showKeyboard(){
        // Abra automaticamente o teclado
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.etSearch, InputMethodManager.SHOW_IMPLICIT)
    }
}