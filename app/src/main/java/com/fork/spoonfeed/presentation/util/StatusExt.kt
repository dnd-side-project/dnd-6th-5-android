package com.fork.spoonfeed.presentation.util

import com.fork.spoonfeed.R
import com.fork.spoonfeed.domain.model.CompanySize

fun getMarriageStatusFromCheckedId(checkedId: Int): Boolean {
    return when (checkedId) {
        R.id.chip_policy_filter_one_marriage_true -> true
        R.id.chip_policy_filter_one_marriage_false -> false
        else -> false
    }
}

fun getEmploymentFromCheckedId(checkedId: Int): Boolean {
    return when (checkedId) {
        R.id.chip_policy_filter_level_two_employment_incumbent -> true
        R.id.chip_policy_filter_level_two_unemployed -> false
        else -> false
    }
}

fun getCompanySizeFromCheckedId(checkedId: Int): CompanySize {
    return when (checkedId) {
        R.id.chip_policy_filter_level_two_company_small -> CompanySize.SMALL
        R.id.chip_policy_filter_level_two_company_mid -> CompanySize.MID
        R.id.chip_policy_filter_level_two_company_self -> CompanySize.SELF
        R.id.chip_policy_filter_level_two_company_founder -> CompanySize.FOUNDER
        R.id.chip_policy_filter_level_two_company_nothing -> CompanySize.NOTHING
        else -> CompanySize.NOTHING
    }
}
