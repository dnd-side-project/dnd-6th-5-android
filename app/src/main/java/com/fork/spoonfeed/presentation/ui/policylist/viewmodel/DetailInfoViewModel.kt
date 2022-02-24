package com.fork.spoonfeed.presentation.ui.policylist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyDetailData
import com.fork.spoonfeed.domain.repository.PolicyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailInfoViewModel @Inject constructor(
    private val policyRepository: PolicyRepository
) : ViewModel() {

    private val _policyDetailInfo = MutableLiveData<ResponsePolicyDetailData.Data.Policy>()
    val policyDetailInfo: LiveData<ResponsePolicyDetailData.Data.Policy>
        get() = _policyDetailInfo

    fun getPolicyDetailInfo(id: Int) {
        try {
            viewModelScope.launch {
                _policyDetailInfo.value = policyRepository.getPolicyDetail(UserData.access_token!!, UserData.platform!!, id).data.policy
            }
        } catch (e: Exception) {

        }
    }

    private val _isLikeClicked = MutableLiveData(false)
    val isLikeClicked: LiveData<Boolean>
        get() = _isLikeClicked
}
