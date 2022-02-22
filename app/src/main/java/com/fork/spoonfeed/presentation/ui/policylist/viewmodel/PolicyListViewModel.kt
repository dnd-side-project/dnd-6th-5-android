package com.fork.spoonfeed.presentation.ui.policylist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import com.fork.spoonfeed.domain.repository.PolicyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PolicyListViewModel @Inject constructor(
    private val policyRepository: PolicyRepository
) : ViewModel() {

    private val _isFilterClicked = MutableLiveData<Boolean>(false)
    val isFilterClicked: LiveData<Boolean>
        get() = _isFilterClicked

    private val _isReWriteClicked = MutableLiveData<Boolean>()
    val isReWriteClicked: LiveData<Boolean>
        get() = _isReWriteClicked

    private val _selectedFilter = MutableLiveData(ALL)
    val selectedFilter: LiveData<String>
        get() = _selectedFilter

    private val _policyFilteredResult = MutableLiveData<List<ResponsePolicyAllData.Data.Policy>>()
    val policyFilteredResult: LiveData<List<ResponsePolicyAllData.Data.Policy>> = _policyFilteredResult

    var initSelectedFilter = ALL

    fun allSelected() {
        _selectedFilter.value = ALL
    }

    fun dwellingSelected() {
        _selectedFilter.value = DWELLING
    }

    fun financeSelected() {
        _selectedFilter.value = FINANCE
    }

    fun nothingSelected() {
        _selectedFilter.value = NOTHING
    }

    fun applyFilter() {
        val filter = _selectedFilter.value ?: return
        viewModelScope.launch {
            _policyFilteredResult.value = policyRepository.getPolicyAll(filter).data.policy
        }
    }

    fun filterOnClick() {
        _isFilterClicked.value = true
    }

    fun filterOnClickFalse() {
        _isFilterClicked.value = false
    }

    //다시입력 클릭 감지
    fun reWriteOnClick() {
        _isReWriteClicked.value = true
    }

    fun reWriteOnClickFalse() {
        _isReWriteClicked.value = false
    }


    companion object {
        const val NOTHING = "NOTHING"
        const val ALL = "전체"
        const val DWELLING = "주거"
        const val FINANCE = "금융"
    }
}
