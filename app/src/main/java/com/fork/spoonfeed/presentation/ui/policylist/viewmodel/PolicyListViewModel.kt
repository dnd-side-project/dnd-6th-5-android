package com.fork.spoonfeed.presentation.ui.policylist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.remote.model.policy.RequestFilteredPolicy
import com.fork.spoonfeed.data.remote.model.policy.RequestPolicyLikeData
import com.fork.spoonfeed.data.remote.model.policy.ResponseFilteredPolicy
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserLikePolicyData
import com.fork.spoonfeed.domain.model.LikeBtnState
import com.fork.spoonfeed.domain.repository.PolicyRepository
import com.fork.spoonfeed.domain.repository.UserRepository
import com.fork.spoonfeed.presentation.base.BaseViewUtil.BaseCategoryBottomDialogFragment.Companion.ALL
import com.fork.spoonfeed.presentation.base.BaseViewUtil.BaseCategoryBottomDialogFragment.Companion.DWELLING
import com.fork.spoonfeed.presentation.base.BaseViewUtil.BaseCategoryBottomDialogFragment.Companion.FINANCE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PolicyListViewModel @Inject constructor(
    private val userRepository: UserRepository,
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

    private val _postMyLikePolicySuccess = MutableLiveData(false)

    private val _myLikePolicyList = MutableLiveData<List<ResponseUserLikePolicyData.Data.Policy>>()
    val myLikePolicyList: LiveData<List<ResponseUserLikePolicyData.Data.Policy>>
        get() = _myLikePolicyList

    private val _getMyLikePolicySuccess = MutableLiveData(false)

    private val _likeBtnState = MutableLiveData<LikeBtnState>()
    val likeBtnState: LiveData<LikeBtnState>
        get() = _likeBtnState

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
            category = if (response.category == FINANCE) FINANCE else DWELLING,
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

    fun postMyLikePolicy(policyId: String) {
        val requestPolicyLiveData = RequestPolicyLikeData(policyId = policyId)
        viewModelScope.launch {
            _postMyLikePolicySuccess.value = policyRepository.postPolicyLike(requestPolicyLiveData).success
        }
    }

    fun getMyLikePolicy() {
        viewModelScope.launch {
            _myLikePolicyList.value = userRepository.getUserLikePolicy().data.policy
            _getMyLikePolicySuccess.value = true
        }
    }

    fun setLikeBtn(likeBtnState: LikeBtnState) {
        _likeBtnState.value = likeBtnState
    }
}

