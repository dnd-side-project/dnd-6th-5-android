package com.fork.spoonfeed.presentation.ui.policy.view.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fork.spoonfeed.domain.model.Age
import com.fork.spoonfeed.domain.model.AnnualIncome
import com.fork.spoonfeed.domain.model.CompanySize
import com.fork.spoonfeed.domain.model.HomeOwnership
import com.fork.spoonfeed.domain.model.HouseHolderStatus
import com.fork.spoonfeed.domain.model.MedianIncome
import com.fork.spoonfeed.domain.model.NetWorth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PolicyFilterViewModel @Inject constructor() : ViewModel() {

    private val _age = MutableLiveData(Age())
    val age: LiveData<Age> = _age

    private val _marriageStatus = MutableLiveData<Boolean>()
    val marriageStatus: LiveData<Boolean> = _marriageStatus

    private val _isLevelOneValid = MutableLiveData(false)
    val isLevelOneValid: LiveData<Boolean> = _isLevelOneValid

    private val _employmentAvailability = MutableLiveData<Boolean>()
    val employmentAvailability: LiveData<Boolean> = _employmentAvailability

    private val _companySize = MutableLiveData<CompanySize>()
    val companySize: LiveData<CompanySize> = _companySize

    private val _isLevelTwoValid = MutableLiveData(false)
    val isLevelTwoValid: LiveData<Boolean> = _isLevelTwoValid

    private val _medianIncome = MutableLiveData<MedianIncome>()
    val medianIncome: LiveData<MedianIncome> = _medianIncome

    private val _annualIncome = MutableLiveData<AnnualIncome>()
    val annualIncome: LiveData<AnnualIncome> = _annualIncome

    private val _netWorth = MutableLiveData<NetWorth>()
    val netWorth: LiveData<NetWorth> = _netWorth

    private val _houseHolderStatus = MutableLiveData<HouseHolderStatus>()
    val houseHolderStatus: LiveData<HouseHolderStatus> = _houseHolderStatus

    private val _homeOwnership = MutableLiveData<HomeOwnership>()
    val homeOwnership: LiveData<HomeOwnership> = _homeOwnership

    private val _saveData = MutableLiveData<Boolean>()
    val saveData: LiveData<Boolean> = _saveData

    private val _isLevelThreeValid = MutableLiveData(false)
    val isLevelThreeValid: LiveData<Boolean> = _isLevelThreeValid

    fun setYear(year: Int?) {
        _age.value = _age.value?.copy(year = year)
        setLevelOneValid()
    }

    fun setMonth(month: Int?) {
        _age.value = _age.value?.copy(month = month)
        setLevelOneValid()
    }

    fun setDay(day: Int?) {
        _age.value = _age.value?.copy(day = day)
        setLevelOneValid()
    }

    fun setMarriageStatus(status: Boolean) {
        _marriageStatus.value = status
        setLevelOneValid()
    }

    private fun setLevelOneValid() {
        _isLevelOneValid.value = _age.value?.isValid() == true && _marriageStatus.value != null
    }

    fun setEmploymentAvailability(availability: Boolean) {
        _employmentAvailability.value = availability
        setLevelTwoValid()
    }

    fun setCompanySize(size: CompanySize) {
        _companySize.value = size
        setLevelTwoValid()
    }

    private fun setLevelTwoValid() {
        _isLevelTwoValid.value =
            _employmentAvailability.value != null && _companySize.value !== null
    }

    fun setMedianIncome(medianIncome: MedianIncome) {
        _medianIncome.value = medianIncome
        setLevelThreeValid()
    }

    fun setAnnualIncome(annualIncome: AnnualIncome) {
        _annualIncome.value = annualIncome
        setLevelThreeValid()
    }

    fun setNetWorth(netWorth: NetWorth) {
        _netWorth.value = netWorth
        setLevelThreeValid()
    }

    fun setHouseHolderStatus(houseHolderStatus: HouseHolderStatus) {
        _houseHolderStatus.value = houseHolderStatus
        setLevelThreeValid()
    }

    fun setHomeOwnership(homeOwnership: HomeOwnership) {
        _homeOwnership.value = homeOwnership
        setLevelThreeValid()
    }

    fun setSaveData(saveData: Boolean) {
        _saveData.value = saveData
    }

    private fun setLevelThreeValid() {
        _isLevelThreeValid.value = _medianIncome.value != null && _annualIncome.value != null
                && _netWorth.value != null && _houseHolderStatus.value != null
                && _homeOwnership.value != null
    }
}
