package com.fork.spoonfeed.presentation.ui.policylist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.policy.RequestFilteredPolicy
import com.fork.spoonfeed.data.remote.model.policy.ResponseFilteredPolicy
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import com.fork.spoonfeed.domain.repository.PolicyRepository
import com.fork.spoonfeed.presentation.ui.policylist.view.BottomDialogFilterFragment.Companion.ALL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PolicyListViewModel @Inject constructor(
    private val policyRepository: PolicyRepository
) : ViewModel() {

    // TODO Refactor: SavedStateHandle로 값 처리
    private var userInfo: RequestFilteredPolicy? = null

    private val _isFilterClicked = MutableLiveData(false)
    val isFilterClicked: LiveData<Boolean>
        get() = _isFilterClicked

    private val _isReWriteClicked = MutableLiveData<Boolean>()
    val isReWriteClicked: LiveData<Boolean>
        get() = _isReWriteClicked

    private val _selectedFilter = MutableLiveData(ALL)
    val selectedFilter: LiveData<String>
        get() = _selectedFilter

    private val _policyFilteredResult = MutableLiveData<List<ResponsePolicyAllData.Data.Policy>>()
    val policyFilteredResult: LiveData<List<ResponsePolicyAllData.Data.Policy>> =
        _policyFilteredResult

    fun setUserInfo(data: RequestFilteredPolicy) {
        userInfo = data
    }

    fun setCategorySelected(category: String) {
        _selectedFilter.value = category
    }

    fun applyFilter() {
        val filter = _selectedFilter.value ?: return

        viewModelScope.launch {
            _policyFilteredResult.value = if (userInfo != null) {
                // TODO 서버 수정되면 POST가 아닌 GET하는 메서드로 변경
                policyRepository.updateUserInfoAndGetFilteredPolicy(userInfo!!).data.policy.mapNotNull {
                    when (filter) {
                        ALL -> {
                            mappingResponseFilteredToPolicy(it)
                        }
                        else -> {
                            if (filter == it.category) {
                                mappingResponseFilteredToPolicy(it)
                            } else {
                                null
                            }
                        }
                    }
                }
            } else {
                policyRepository.getPolicyAll(filter).data.policy
            }
        }
    }

    private fun mappingResponseFilteredToPolicy(
        response: ResponseFilteredPolicy.Data.Policy
    ): ResponsePolicyAllData.Data.Policy {
        return ResponsePolicyAllData.Data.Policy(
            id = response.id,
            name = response.name,
            category = response.category,
            summary = response.summary,
            applicationPeriod = response.applicationPeriod,
            likeCount = response.likeCnt
        )
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
}
