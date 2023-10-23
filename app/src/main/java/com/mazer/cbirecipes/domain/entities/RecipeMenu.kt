package com.mazer.cbirecipes.domain.entities

data class RecipeMenu(
    val id: Int,
    val name: String,
    val duration: String,
    val urlThumb: String,
    val isVegan: Boolean
)