package com.fork.spoonfeed.presentation.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.fork.spoonfeed.R
import com.fork.spoonfeed.domain.model.AgeInputType
import com.fork.spoonfeed.domain.model.ChipInputType
import com.fork.spoonfeed.presentation.ui.policy.view.filter.PolicyFilterViewModel
import com.google.android.material.chip.ChipGroup

fun AppCompatActivity.replace(fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.fcv_main, fragment, null)
        .commit()
}

fun AppCompatActivity.addAndAddToBackStack(fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .add(R.id.fcv_policy_filter, fragment)
        .addToBackStack(null)
        .commit()
}

@BindingAdapter("onCheckedChanged")
fun setOnCheckedChanged(view: CheckBox, viewModel: ViewModel){
    view.setOnCheckedChangeListener { _, isChecked ->
        (viewModel as? PolicyFilterViewModel)?.setSaveData(isChecked)
    }
}

@BindingAdapter("viewModel", "type")
fun setOnCheckedChanged(view: ChipGroup, viewModel: ViewModel, type: ChipInputType) {
    view.setOnCheckedChangeListener { _, checkedId ->
        when (type) {
            ChipInputType.MARRIAGE -> {
                (viewModel as? PolicyFilterViewModel)
                    ?.setMarriageStatus(getMarriageStatusFromCheckedId(checkedId))
            }
            ChipInputType.EMPLOYMENT -> {
                (viewModel as? PolicyFilterViewModel)
                    ?.setEmploymentAvailability(getEmploymentFromCheckedId(checkedId))
            }
            ChipInputType.COMPANY_SIZE -> {
                (viewModel as? PolicyFilterViewModel)
                    ?.setCompanySize(getCompanySizeFromCheckedId(checkedId))
            }
            ChipInputType.MEDIAN_INCOME -> {
                (viewModel as? PolicyFilterViewModel)
                    ?.setMedianIncome(getMedianIncomeFromCheckedId(checkedId))
            }
            ChipInputType.ANNUAL_INCOME -> {
                (viewModel as? PolicyFilterViewModel)
                    ?.setAnnualIncome(getAnnualIncomeFromCheckedId(checkedId))
            }
            ChipInputType.NET_WORTH -> {
                (viewModel as? PolicyFilterViewModel)
                    ?.setNetWorth(getNetWorthFromCheckedId(checkedId))
            }
            ChipInputType.HOUSE_HOLDER_STATUS -> {
                (viewModel as? PolicyFilterViewModel)
                    ?.setHouseHolderStatus(getHouseHolderStatusFromCheckedId(checkedId))
            }
            ChipInputType.HOME_OWNERSHIP -> {
                (viewModel as? PolicyFilterViewModel)
                    ?.setHomeOwnership(getHomeOwnershipFromCheckedId(checkedId))
            }
        }
    }
}

@BindingAdapter("viewModel", "ageInputType")
fun addTextChangeListener(view: EditText, viewModel: ViewModel, ageInputType: AgeInputType) {
    view.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (ageInputType == AgeInputType.YEAR) {
                (viewModel as? PolicyFilterViewModel)?.setYear(getIntFromCharSequence(s))
            } else {
                if (ageInputType == AgeInputType.MONTH) {
                    (viewModel as? PolicyFilterViewModel)?.setMonth(getIntFromCharSequence(s))
                } else {
                    (viewModel as? PolicyFilterViewModel)?.setDay(getIntFromCharSequence(s))
                }
                s?.let { formatStringWithPrefix(it.toString(), view) }
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}

private fun formatStringWithPrefix(input: String, editText: EditText) {
    val formattedText = when (input.length) {
        1 -> {
            "0$input"
        }
        3 -> {
            if (input.startsWith("0")) {
                input.toInt().toString()
            } else {
                input.substring(0 until input.lastIndex)
            }
        }
        else -> {
            return
        }
    }
    editText.setText(formattedText)
    editText.setSelection(editText.text.length)
}
