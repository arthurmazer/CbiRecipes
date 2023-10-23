package com.mazer.cbirecipes.presentation.ui.custom_views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.mazer.cbirecipes.R
import com.mazer.cbirecipes.databinding.ChipLayoutBinding

class ChipView : ConstraintLayout {

    private lateinit var typedArray: TypedArray
    private lateinit var binding: ChipLayoutBinding

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

    var label: String? = null
        set(value) {
            field = value
            setTextLabel(value)
        }

    var isChipEnabled: Boolean = false
        set(value) {
            field = value
            changeChipEnabled(value)
        }

    var ivLogoUrl: String? = null
        set(value) {
            field = value
            setLogoImage(value)
        }

    private fun init(context: Context, attrs: AttributeSet? = null) {
        try {
            binding = ChipLayoutBinding.inflate(LayoutInflater.from(context), this, true)

            if (attrs != null) {
                typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChipView)
            }

            getSelectedAttributes()
        } catch (e: Exception) {
            throw Exception()
        }
        requestLayout()
    }

    private fun getSelectedAttributes() {
        label = typedArray.getString(R.styleable.ChipView_chip_label)
        isChipEnabled = typedArray.getBoolean(R.styleable.ChipView_chip_enabled, false)
    }

    private fun changeChipEnabled(isEnabled: Boolean) {
        val ctx = binding.root.context
        if (isEnabled) {
            binding.tvRecipeFilter.setTypeface(binding.tvRecipeFilter.typeface, Typeface.BOLD)
            binding.tvRecipeFilter.setTextColor(ContextCompat.getColor(ctx, R.color.colorPrimary))
            binding.ivFilterRecipeIcon.background = ContextCompat.getDrawable(context, R.drawable.chip_selected_background)
        } else {
            binding.tvRecipeFilter.setTypeface(binding.tvRecipeFilter.typeface, Typeface.NORMAL)
            binding.tvRecipeFilter.setTextColor(ContextCompat.getColor(ctx, R.color.black))
            binding.ivFilterRecipeIcon.background = ContextCompat.getDrawable(context, R.drawable.chip_background)
        }
    }

    private fun setTextLabel(label: String?) {
        binding.tvRecipeFilter.text = label ?: ""
    }

    private fun setLogoImage(logo: String?) {
        Glide.with(binding.root.context).load(logo ?: return).into(binding.ivFilterRecipeIcon)
    }
}