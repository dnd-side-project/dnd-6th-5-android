package com.fork.spoonfeed.presentation.util

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.fork.spoonfeed.R
import com.fork.spoonfeed.domain.model.AgeInputType
import com.fork.spoonfeed.domain.model.ChipInputType
import com.fork.spoonfeed.presentation.ui.communitypost.view.UserReportReasonActivity
import com.fork.spoonfeed.presentation.ui.communitypost.viewmodel.ReportViewModel
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageMyInfoViewModel
import com.fork.spoonfeed.presentation.ui.policy.view.filter.PolicyFilterViewModel
import com.google.android.material.chip.ChipGroup
import kotlin.math.roundToInt

fun AppCompatActivity.add(fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .add(R.id.fcv_main, fragment, null)
        .commit()
}

fun AppCompatActivity.replace(fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.fcv_main, fragment, null)
        .commit()
}

fun AppCompatActivity.addAndAddToBackStack(layoutId: Int, fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .add(layoutId, fragment)
        .addToBackStack(null)
        .commit()
}

@BindingAdapter("onCheckedChanged")
fun setOnCheckedChanged(view: CheckBox, viewModel: ViewModel) {
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
                (viewModel as? MyPageMyInfoViewModel)
                    ?.setMarriageStatus(getMarriageStatusFromCheckedId(checkedId))
            }
            ChipInputType.EMPLOYMENT -> {
                (viewModel as? PolicyFilterViewModel)
                    ?.setEmploymentAvailability(getEmploymentFromCheckedId(checkedId))
                (viewModel as? MyPageMyInfoViewModel)
                    ?.setEmploymentAvailability(getEmploymentFromCheckedId(checkedId))
            }
            ChipInputType.COMPANY_SIZE -> {
                (viewModel as? PolicyFilterViewModel)
                    ?.setCompanySize(getCompanySizeFromCheckedId(checkedId))
                (viewModel as? MyPageMyInfoViewModel)
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
                (viewModel as? MyPageMyInfoViewModel)?.setYear(getIntFromCharSequence(s))
            } else {
                if (ageInputType == AgeInputType.MONTH) {
                    (viewModel as? PolicyFilterViewModel)?.setMonth(getIntFromCharSequence(s))
                    (viewModel as? MyPageMyInfoViewModel)?.setMonth(getIntFromCharSequence(s))
                } else {
                    (viewModel as? PolicyFilterViewModel)?.setDay(getIntFromCharSequence(s))
                    (viewModel as? MyPageMyInfoViewModel)?.setDay(getIntFromCharSequence(s))
                }
                s?.let { formatStringWithPrefix(it.toString(), view) }
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}

@BindingAdapter("viewModel", "reasonNumber", "isSelected")
fun setOnCheckedChanged(
    view: ImageButton,
    viewModel: ViewModel,
    reasonNumber: String,
    selected: Boolean
) {
    view.setOnClickListener {
        when (reasonNumber) {
            UserReportReasonActivity.REPORT_REASON_ONE -> (viewModel as? ReportViewModel)?.setReportReasonOneStatus(
                !view.isSelected
            )
            UserReportReasonActivity.REPORT_REASON_TWO -> (viewModel as? ReportViewModel)?.setReportReasonTwoStatus(
                !view.isSelected
            )
            UserReportReasonActivity.REPORT_REASON_THREE -> (viewModel as? ReportViewModel)?.setReportReasonThreeStatus(
                !view.isSelected
            )
            UserReportReasonActivity.REPORT_REASON_FOUR -> (viewModel as? ReportViewModel)?.setReportReasonFourStatus(
                !view.isSelected
            )
            UserReportReasonActivity.REPORT_REASON_FIVE -> (viewModel as? ReportViewModel)?.setReportReasonFiveStatus(
                !view.isSelected
            )
            UserReportReasonActivity.REPORT_REASON_SIX -> (viewModel as? ReportViewModel)?.setReportReasonSixStatus(
                !view.isSelected
            )
        }
    }
    view.isSelected = selected
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


fun Activity.setBackBtnClickListener(imageView: ImageView) {
    imageView.setOnClickListener {
        finish()
    }
}

fun Context.dpToPx(dp: Int): Int {
    val displayMetrics = resources.displayMetrics
    return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
}

fun SearchView.setTextColor(context: Context) {
    findViewById<EditText>(R.id.search_src_text).setHintTextColor(context.getColor(R.color.gray03))
    findViewById<EditText>(R.id.search_src_text).setTextColor(context.getColor(R.color.gray06))
}

fun SearchView.setTextSize(size: Float) {
    findViewById<EditText>(R.id.search_src_text).textSize = size
}
