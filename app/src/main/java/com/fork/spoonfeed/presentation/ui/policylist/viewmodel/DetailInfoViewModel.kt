package com.fork.spoonfeed.presentation.ui.policylist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.policy.RequestPolicyLikeData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyDetailData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserLikePolicyData
import com.fork.spoonfeed.domain.repository.PolicyRepository
import com.fork.spoonfeed.domain.repository.UserRepository
import com.fork.spoonfeed.presentation.ui.policylist.view.DetailInfoActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailInfoViewModel @Inject constructor(
    private val policyRepository: PolicyRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _policyDetailInfo = MutableLiveData<ResponsePolicyDetailData.Data.Policy>()
    val policyDetailInfo: LiveData<ResponsePolicyDetailData.Data.Policy>
        get() = _policyDetailInfo

    private val _myLikePolicyList = MutableLiveData<List<ResponseUserLikePolicyData.Data.Policy>>()
    val myLikePolicyList: LiveData<List<ResponseUserLikePolicyData.Data.Policy>>
        get() = _myLikePolicyList

    private val _postMyLikePolicySuccess = MutableLiveData(false)
    val postMyLikePolicySuccess: LiveData<Boolean>
        get() = _postMyLikePolicySuccess

    private val _isLikeClicked = MutableLiveData(false)
    val isLikeClicked: LiveData<Boolean>
        get() = _isLikeClicked

    private val _referenceSiteOne = MutableLiveData<String>("")
    val referenceSiteOne: LiveData<String>
        get() = _referenceSiteOne

    private val _referenceSiteTwo = MutableLiveData<String>()
    val referenceSiteTwo: LiveData<String>
        get() = _referenceSiteTwo

    fun getPolicyDetailInfo(id: Int) {
        try {
            viewModelScope.launch {
                _policyDetailInfo.value = policyRepository.getPolicyDetail(UserData.accessToken!!, UserData.platform!!, id).data.policy
                _referenceSiteOne.value = _policyDetailInfo.value?.referenceSite1 ?: DetailInfoActivity.REFERENCE_SITE_NOTHING
                _referenceSiteTwo.value = _policyDetailInfo.value?.referenceSite2 ?: DetailInfoActivity.REFERENCE_SITE_NOTHING
            }
        } catch (e: Exception) {
        }
    }

    fun getMyLikePolicy() {
        viewModelScope.launch {
            _myLikePolicyList.value = userRepository.getUserLikePolicy().data.policy
        }
    }

    fun postMyLikePolicy(id: String) {
        val requestPolicyLiveData = RequestPolicyLikeData(policyId = id)
        viewModelScope.launch {
            _postMyLikePolicySuccess.value = policyRepository.postPolicyLike(requestPolicyLiveData).success
        }
    }
}
