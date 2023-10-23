package com.mazer.cbirecipes.presentation.ui.custom_views


import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.mazer.cbirecipes.databinding.SearchViewLayoutBinding

class SearchView : ConstraintLayout {

    private lateinit var binding: SearchViewLayoutBinding

    constructor(context: Context) :
            super(context) {
        init(context)
    }
    constructor(context: Context, attrs: AttributeSet?) :
            super(context, attrs) {
        init(context, attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) :
            super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet? = null) {
        try {
            binding = SearchViewLayoutBinding.inflate(LayoutInflater.from(context), this, true)
        } catch (e: Exception) {
            throw Exception()
        }
        requestLayout()
    }

}