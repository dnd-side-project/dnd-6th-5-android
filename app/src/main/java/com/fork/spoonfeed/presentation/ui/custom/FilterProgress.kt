package com.fork.spoonfeed.presentation.ui.custom

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FilterProgressBinding

class FilterProgress constructor(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {

    private var binding: FilterProgressBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.filter_progress, this, true)

    private val currentPosition = context.obtainStyledAttributes(attrs, R.styleable.FilterProgress)
        .getInteger(R.styleable.FilterProgress_currentPosition, 1)

    init {
        initProgressActive()
    }

    private fun initProgressActive() {
        when (currentPosition) {
            1 -> {
                setProgressView(twoState = false, threeState = false)
            }
            2 -> {
                setProgressView(twoState = true, threeState = false)
            }
            3 -> {
                setProgressView(twoState = true, threeState = true)
            }
        }
    }

    private fun setProgressView(twoState: Boolean, threeState: Boolean) {
        val primaryBlue =
            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.primary_blue))
        val gray02 = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.gray02))
        if (twoState) {
            binding.viewPolicyFilterProgressTwo.backgroundTintList = primaryBlue
            binding.tvPolicyFilterProgressTwo.visibility = VISIBLE
            binding.tvPolicyFilterProgressOne.visibility = INVISIBLE
        } else {
            binding.viewPolicyFilterProgressTwo.backgroundTintList = gray02
            binding.tvPolicyFilterProgressTwo.visibility = INVISIBLE
        }

        if (threeState) {
            binding.viewPolicyFilterProgressThree.backgroundTintList = primaryBlue
            binding.tvPolicyFilterProgressThree.visibility = VISIBLE
            binding.tvPolicyFilterProgressOne.visibility = INVISIBLE
            binding.tvPolicyFilterProgressTwo.visibility = INVISIBLE
        } else {
            binding.viewPolicyFilterProgressThree.backgroundTintList = gray02
            binding.tvPolicyFilterProgressThree.visibility = INVISIBLE
        }
    }
}
