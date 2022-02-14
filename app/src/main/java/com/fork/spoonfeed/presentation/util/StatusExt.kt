package com.fork.spoonfeed.presentation.util

import com.fork.spoonfeed.R
import com.fork.spoonfeed.domain.model.AnnualIncome
import com.fork.spoonfeed.domain.model.CompanySize
import com.fork.spoonfeed.domain.model.HomeOwnership
import com.fork.spoonfeed.domain.model.HouseHolderStatus
import com.fork.spoonfeed.domain.model.MedianIncome
import com.fork.spoonfeed.domain.model.NetWorth

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

fun getMedianIncomeFromCheckedId(checkedId: Int): MedianIncome {
    return when (checkedId) {
        R.id.chip_policy_filter_level_three_median_income_under_30 -> MedianIncome.UNDER_30
        R.id.chip_policy_filter_level_three_median_income_under_40 -> MedianIncome.UNDER_40
        R.id.chip_policy_filter_level_three_median_income_under_45 -> MedianIncome.UNDER_45
        R.id.chip_policy_filter_level_three_median_income_under_50 -> MedianIncome.UNDER_50
        R.id.chip_policy_filter_level_three_median_income_under_72 -> MedianIncome.UNDER_72
        R.id.chip_policy_filter_level_three_median_income_under_100 -> MedianIncome.UNDER_100
        R.id.chip_policy_filter_level_three_median_income_nothing -> MedianIncome.NOTHING
        R.id.chip_policy_filter_level_three_median_income_private -> MedianIncome.PRIVATE
        else -> MedianIncome.PRIVATE
    }
}

fun getAnnualIncomeFromCheckedId(checkedId: Int): AnnualIncome {
    return when (checkedId) {
        R.id.chip_policy_filter_level_three_annual_income_couple_under_2 -> AnnualIncome.COUPLE_UNDER_2
        R.id.chip_policy_filter_level_three_annual_income_couple_under_5 -> AnnualIncome.COUPLE_UNDER_5
        R.id.chip_policy_filter_level_three_annual_income_single_under_3 -> AnnualIncome.SINGLE_UNDER_3
        R.id.chip_policy_filter_level_three_annual_income_single_under_3_5 -> AnnualIncome.SINGLE_UNDER_3_5
        R.id.chip_policy_filter_level_three_annual_income_nothing -> AnnualIncome.NOTHING
        R.id.chip_policy_filter_level_three_annual_income_private -> AnnualIncome.PRIVATE
        else -> AnnualIncome.PRIVATE
    }
}

fun getNetWorthFromCheckedId(checkedId: Int): NetWorth {
    return when (checkedId) {
        R.id.chip_policy_filter_level_three_net_worth_under_2_92 -> NetWorth.UNDER_2_92
        R.id.chip_policy_filter_level_three_net_worth_over_2_92 -> NetWorth.OVER_2_92
        R.id.chip_policy_filter_level_three_net_worth_private -> NetWorth.PRIVATE
        else -> NetWorth.PRIVATE
    }
}

fun getHouseHolderStatusFromCheckedId(checkedId: Int): HouseHolderStatus {
    return when (checkedId) {
        R.id.chip_policy_filter_level_three_house_holder_status_owner -> HouseHolderStatus.OWNER
        R.id.chip_policy_filter_level_three_house_holder_status_member -> HouseHolderStatus.MEMBER
        R.id.chip_policy_filter_level_three_house_holder_status_private -> HouseHolderStatus.PRIVATE
        else -> HouseHolderStatus.PRIVATE
    }
}

fun getHomeOwnershipFromCheckedId(checkedId: Int): HomeOwnership {
    return when (checkedId) {
        R.id.chip_policy_filter_level_three_home_ownership_owner -> HomeOwnership.OWNER
        R.id.chip_policy_filter_level_three_home_ownership_homeless -> HomeOwnership.HOMELESS
        R.id.chip_policy_filter_level_three_home_ownership_private -> HomeOwnership.PRIVATE
        else -> HomeOwnership.PRIVATE
    }
}
