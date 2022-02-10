package com.fork.spoonfeed.presentation.ui.policy.view.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fork.spoonfeed.domain.model.Age
import com.fork.spoonfeed.domain.model.CompanySize
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PolicyFilterViewModel @Inject constructor() : ViewModel() {

    private val _age = MutableLiveData<Age>()
    val age: LiveData<Age> = _age

    private val _marriageStatus = MutableLiveData<Boolean>()
    val marriageStatus: LiveData<Boolean> = _marriageStatus

    private val _employmentAvailability = MutableLiveData<Boolean>()
    val employmentAvailability: LiveData<Boolean> = _employmentAvailability

    private val _companySize = MutableLiveData<CompanySize>()
    val companySize: LiveData<CompanySize> = _companySize

    fun setAge(year: Int? = null, month: Int? = null, day: Int? = null) {
        val newYear = year.let { if (it == 0) null else it ?: _age.value?.year }
        val newMonth = month.let { if (it == 0) null else it ?: _age.value?.month }
        val newDay = day.let { if (it == 0) null else it ?: _age.value?.day }

        _age.value = Age(newYear, newMonth, newDay)
    }

    fun setMarriageStatus(status: Boolean) {
        _marriageStatus.value = status
    }

    fun isLevelOneValid(): Boolean {
        return _age.value?.isValid() == true && _marriageStatus.value != null
    }

    fun setEmploymentAvailability(availability: Boolean) {
        _employmentAvailability.value = availability
    }

    fun setCompanySize(size: CompanySize) {
        _companySize.value = size
    }

    fun isLevelTwoValid(): Boolean {
        return _employmentAvailability.value != null && _companySize.value !== null
    }
}


