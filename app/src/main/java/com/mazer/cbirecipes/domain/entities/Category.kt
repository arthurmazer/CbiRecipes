package com.mazer.cbirecipes.domain.entities

import com.google.gson.annotations.Expose

data class Category(
    val name: String,
    val urlImg: String,
    @Expose(deserialize = false, serialize = false)
    var isSelected: Boolean
)
