package com.fork.spoonfeed.presentation.ui.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.policy.RequestFilteredPolicy
import com.fork.spoonfeed.data.remote.model.user.RequestPatchUserFilterData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserData
import com.fork.spoonfeed.domain.model.Age
import com.fork.spoonfeed.domain.model.CompanySize
import com.fork.spoonfeed.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageMyInfoViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _initialUserData = MutableLiveData<ResponseUserData.Data.User>()
    val initialUserInfo: LiveData<ResponseUserData.Data.User> = _initialUserData

    var updatedUserData: ResponseUserData.Data.User? = null

    private val _isPatchUserFilterValid = MutableLiveData(false)
    val isPatchUserFilterValid: LiveData<Boolean> = _isPatchUserFilterValid

    private val _age = MutableLiveData(Age())
    val age: LiveData<Age> = _age

    private val _marriageStatus = MutableLiveData<Boolean>()
    val marriageStatus: LiveData<Boolean> = _marriageStatus

    private val _employmentAvailability = MutableLiveData<Boolean?>()
    val employmentAvailability: LiveData<Boolean?> = _employmentAvailability

    private val _companySize = MutableLiveData<CompanySize?>()
    val companySize: LiveData<CompanySize?> = _companySize

    private fun setIsPatchUserFilterValid() {
        _isPatchUserFilterValid.value = _age.value?.isValid() == true
    }

    fun getUserData() {
        viewModelScope.launch {
            userRepository.getUserData().data.user.let {
                _initialUserData.value = it
                updatedUserData = it
            }
        }
    }

    fun setYear(year: Int?) {
        _age.value = _age.value?.copy(year = year)
        setIsPatchUserFilterValid()
    }

    fun setMonth(month: Int?) {
        _age.value = _age.value?.copy(month = month)
        setIsPatchUserFilterValid()
    }

    fun setDay(day: Int?) {
        _age.value = _age.value?.copy(day = day)
        setIsPatchUserFilterValid()
    }

    fun setMarriageStatus(status: Boolean) {
        _marriageStatus.value = status
    }

    fun updateMedianIncome(data: String) {
        updatedUserData = updatedUserData?.copy(
            medianIncome = data
        )
    }

    fun updateAnnualIncome(data: String) {
        updatedUserData = updatedUserData?.copy(
            annualIncome = data
        )
    }

    fun updateAsset(data: String) {
        updatedUserData = updatedUserData?.copy(
            asset = data
        )
    }

    fun updateHouseOwner(data: String) {
        updatedUserData = updatedUserData?.copy(
            isHouseOwner = data
        )
    }

    fun updateHasHouse(data: String) {
        updatedUserData = updatedUserData?.copy(
            hasHouse = data
        )
    }

    fun patchUserFilter() {
        val age = _age.value?.formatAge() ?: return
        val updatedUserData = updatedUserData ?: return
        val requestPatchUserFilterData = RequestPatchUserFilterData(
            id = UserData.id.toString(),
            age = age,
            maritalStatus = updatedUserData.maritalStatus,
            workStatus = updatedUserData.workStatus,
            companyScale = updatedUserData.companyScale,
            medianIncome = updatedUserData.medianIncome,
            annualIncome = updatedUserData.annualIncome,
            asset = updatedUserData.asset,
            isHouseOwner = updatedUserData.isHouseOwner,
            hasHouse = updatedUserData.hasHouse
        )

        viewModelScope.launch {
            userRepository.patchUserFilter(requestPatchUserFilterData)
        }
    }
}