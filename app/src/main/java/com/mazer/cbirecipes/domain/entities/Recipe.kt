package com.mazer.cbirecipes.domain.entities

data class Recipe(
    val name: String,
    val duration: String,
    val description: String,
    val urlTeaserVideo: String,
    val servings: String,
    val steps: List<Steps>,
    val ingredients: List<Ingredient>
)