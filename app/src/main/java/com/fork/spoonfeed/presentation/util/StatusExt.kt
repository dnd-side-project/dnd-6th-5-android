package com.fork.spoonfeed.presentation.util

import com.fork.spoonfeed.R

fun getMarriageStatusFromCheckedId(checkedId: Int): Boolean {
    return when (checkedId) {
        R.id.chip_policy_filter_one_marriage_true -> true
        R.id.chip_policy_filter_one_marriage_false -> false
        else -> false
    }
}
