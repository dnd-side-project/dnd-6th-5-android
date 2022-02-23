package com.fork.spoonfeed.data.remote.model.policy

data class RequestFilteredPolicy(
    val id: String,
    val age: String,
    val maritalStatus: String,
    val workStatus: String,
    val companyScale: String,
    val medianIncome: String,
    val annualIncome: String,
    val asset: String,
    val isHouseOwner: String,
    val hasHouse: String,
)
