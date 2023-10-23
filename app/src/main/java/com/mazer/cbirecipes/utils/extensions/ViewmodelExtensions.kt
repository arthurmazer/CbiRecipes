package com.mazer.cbirecipes.utils.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *  This method that creates a MutableLiveData and return it as a LiveData
 *
 *  @param T type of live data
 *  @param value value to be set on the live data
 *  @see LiveDataSetter
 */
fun <T> ViewModel.liveData(value: T) = lazy {
    MutableLiveData(value) as LiveData<T>
}

/**
 *  This method that creates a MutableLiveData and return it as a LiveData
 *
 *  @param T type of live data
 *  @see LiveDataSetter
 */
fun <T> ViewModel.liveData() = lazy {
    MutableLiveData<T>() as LiveData<T>
}