package com.mazer.cbirecipes.utils.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> LiveData<T>.asMutable(): MutableLiveData<T>? {
    return if (this is MutableLiveData) {
        this
    } else {
        null
    }
}