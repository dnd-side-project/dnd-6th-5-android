package com.fork.spoonfeed.presentation.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyDetailData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserLikePolicyData
import com.fork.spoonfeed.domain.repository.PolicyRepository
import com.fork.spoonfeed.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _myLikePolicyList = MutableLiveData<List<ResponseUserLikePolicyData.Data.Policy>>()
    val myLikePolicyList: LiveData<List<ResponseUserLikePolicyData.Data.Policy>>
        get() = _myLikePolicyList

    private val _isMyLikePolicyListEmpty = MutableLiveData(true)
    val isMyLikePolicyListEmpty: LiveData<Boolean>
        get() = _isMyLikePolicyListEmpty

    fun getMyLikePolicy() {
        viewModelScope.launch {
            _myLikePolicyList.value = userRepository.getUserLikePolicy().data.policy
            _isMyLikePolicyListEmpty.value = _myLikePolicyList.value!![0].name.isEmpty() == true
        }
    }
}

