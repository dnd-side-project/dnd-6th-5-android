package com.fork.spoonfeed.data.remote.model.user

data class RequestPatchUserFilterData(
    val id: String,
    val age: String,
    val workStatus: String,
    val companyScale: String,
    val medianIncome: String,
    val annualIncome: String,
    val asset: String,
    val hasHouse: String,
    val isHouseOwner: String,
    val maritalStatus: String,

    )