package com.fork.spoonfeed.presentation.ui.mypage.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.policy.RequestFilteredPolicy
import com.fork.spoonfeed.data.remote.model.user.RequestPatchUserFilterData
import com.fork.spoonfeed.data.remote.model.user.RequestUserNickNameData
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

    private val _isPatchUserFilterSuccess = MutableLiveData(false)
    val isPatchUserFilterSuccess: LiveData<Boolean> = _isPatchUserFilterSuccess

    private val _age = MutableLiveData(Age())
    val age: LiveData<Age> = _age

    private val _marriageStatus = MutableLiveData<Boolean>()
    val marriageStatus: LiveData<Boolean> = _marriageStatus

    private val _employmentAvailability = MutableLiveData<Boolean?>()
    val employmentAvailability: LiveData<Boolean?> = _employmentAvailability

    private val _companySize = MutableLiveData<CompanySize?>()
    val companySize: LiveData<CompanySize?> = _companySize

    private val _userNickName = MutableLiveData<String>()
    val userNickName: LiveData<String> = _userNickName

    private val _isPatchNickNameVaild = MutableLiveData(false)
    val isPatchNickNameVaild: LiveData<Boolean> get() = _isPatchNickNameVaild

    private val _isPatchNickNameSuccess = MutableLiveData(false)
    val isPatchNickNameSuccess: LiveData<Boolean> get() = _isPatchNickNameSuccess

    private val _isPatchUserInfoSuccess = MutableLiveData(false)
    val isPatchUserInfoSuccess: LiveData<Boolean> get() = _isPatchUserInfoSuccess

    private val _isNameSpecial = MutableLiveData<Boolean>()
    val isNameSpecial: LiveData<Boolean> = _isNameSpecial

    private val _name = MutableLiveData<String?>()
    val name: LiveData<String?> = _name

    fun isPatchUserInfoSuccess() {
        _isPatchUserInfoSuccess.value = _isPatchUserFilterSuccess.value == true && _isPatchNickNameSuccess.value == true
    }

    fun setPatchUserNickNameVaild() {
        _isPatchNickNameVaild.value = true
        setIsPatchUserFilterValid()
    }

    fun setPatchUserNickNameInVaild() {
        _isPatchNickNameVaild.value = false
        setIsPatchUserFilterValid()
    }

    private fun setIsPatchUserFilterValid() {
        _isPatchUserFilterValid.value = _age.value?.isValid() == true && _isPatchNickNameVaild.value == true
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
        setIsPatchUserFilterValid()
    }

    fun setEmploymentAvailability(status: Boolean) {
        _employmentAvailability.value = status
        setIsPatchUserFilterValid()
    }

    fun setCompanySize(size: CompanySize) {
        _companySize.value = size
        setIsPatchUserFilterValid()
    }

    fun updateMedianIncome(data: String) {
        updatedUserData = updatedUserData?.copy(
            medianIncome = data
        )
        setIsPatchUserFilterValid()
    }

    fun updateAnnualIncome(data: String) {
        updatedUserData = updatedUserData?.copy(
            annualIncome = data
        )
        setIsPatchUserFilterValid()
    }

    fun updateAsset(data: String) {
        updatedUserData = updatedUserData?.copy(
            asset = data
        )
        setIsPatchUserFilterValid()
    }

    fun updateHouseOwner(data: String) {
        updatedUserData = updatedUserData?.copy(
            isHouseOwner = data
        )
        setIsPatchUserFilterValid()
    }

    fun updateHasHouse(data: String) {
        updatedUserData = updatedUserData?.copy(
            hasHouse = data
        )
        setIsPatchUserFilterValid()
    }

    fun getNickName() {
        viewModelScope.launch {
            _userNickName.value = userRepository.getUserData().data.user.nickname
        }
    }

    fun patchUserNickName() {
        val userNickName = _name.value ?: return
        viewModelScope.launch {
            kotlin.runCatching {
                val requestUserNickNameData = RequestUserNickNameData(UserData.id.toString(), userNickName)
                userRepository.patchUserNickName(UserData.accessToken!!, UserData.platform!!, requestUserNickNameData).data.user.nickname
            }.onSuccess {
                _isPatchNickNameSuccess.setValue(true)
                isPatchUserInfoSuccess()
            }.onFailure {
                _isPatchNickNameSuccess.setValue(false)
                isPatchUserInfoSuccess()
            }
        }
    }

    fun patchUserFilter() {
        val age = _age.value?.formatAge() ?: return
        val maritalStatus = _marriageStatus.value?.let { if (it) "기혼" else "미혼" } ?: return
        val workStatus = _employmentAvailability.value?.let { if (it) "재직자" else "미취업자" } ?: return
        val companyScale = _companySize.value?.value ?: return
        val updatedUserData = updatedUserData ?: return
        val requestPatchUserFilterData = RequestPatchUserFilterData(
            id = UserData.id.toString(),
            age = age,
            maritalStatus = maritalStatus,
            workStatus = workStatus,
            companyScale = companyScale,
            medianIncome = updatedUserData.medianIncome,
            annualIncome = updatedUserData.annualIncome,
            asset = updatedUserData.asset,
            isHouseOwner = updatedUserData.isHouseOwner,
            hasHouse = updatedUserData.hasHouse
        )
        viewModelScope.launch {
            _isPatchUserFilterSuccess.value = userRepository.patchUserFilter(requestPatchUserFilterData).success
            isPatchUserInfoSuccess()
        }
    }

    fun setUserNickName(userNickName: String) {
        _name.value = userNickName
        checkUserNickName(userNickName)
    }

    fun checkUserNickName(userNickName: String) {
        viewModelScope.launch {
            _isNameSpecial.value = userRepository.checkUserNameDuplicate(userNickName).success
        }
    }
}