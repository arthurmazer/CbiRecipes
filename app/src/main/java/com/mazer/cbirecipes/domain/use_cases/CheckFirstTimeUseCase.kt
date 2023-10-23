package com.mazer.cbirecipes.domain.use_cases

import android.content.SharedPreferences
import com.mazer.cbirecipes.utils.constants.Constants.SHARED_KEY_FIRST_TIME

class CheckFirstTimeUseCase(private val sharedPreferences: SharedPreferences) {

    fun isFirstTimeUser(): Boolean {
        return sharedPreferences.getBoolean(SHARED_KEY_FIRST_TIME, true)
    }

     fun setFirstTimeUser(){
        sharedPreferences.edit().putBoolean(SHARED_KEY_FIRST_TIME, false).apply()
    }

}