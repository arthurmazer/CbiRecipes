package com.mazer.cbirecipes.domain.entities

data class Steps (
    val stepIndex: Int,
    val name: String,
    val description: String,
    val urlImg: String,
    val urlVideo: String,
    val ingredientsUsed: List<Ingredient>
)