package com.fork.spoonfeed.presentation.ui.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserCommentData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserPostData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserUserLikePolicyData
import com.fork.spoonfeed.domain.repository.AuthRepository
import com.fork.spoonfeed.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _isQuestionValid = MutableLiveData(false)
    val isQuestionValid: LiveData<Boolean>
        get() = _isQuestionValid

    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    private val _sentence = MutableLiveData<String>()
    val sentence: LiveData<String>
        get() = _sentence

    private val _myPostList = MutableLiveData<List<ResponseUserPostData.Data.Post>>()
    val myPostList: LiveData<List<ResponseUserPostData.Data.Post>> =
        _myPostList

    private val _isMyPostEmpty = MutableLiveData<Boolean>(true)
    val isMyPostEmpty: LiveData<Boolean>
        get() = _isMyPostEmpty

    private val _myCommentList = MutableLiveData<List<ResponseUserCommentData.Data.Comment>>()
    val myCommentList: LiveData<List<ResponseUserCommentData.Data.Comment>> =
        _myCommentList

    private val _isMyCommentEmpty = MutableLiveData<Boolean>(true)
    val isMyCommentEmpty: LiveData<Boolean>
        get() = _isMyCommentEmpty

    private val _myInterastedtPoLicyList = MutableLiveData<List<ResponseUserUserLikePolicyData.Data.Policy>>()
    val myInterastedtPoLicyList: LiveData<List<ResponseUserUserLikePolicyData.Data.Policy>>
        get() = _myInterastedtPoLicyList

    private val _isMyInterastedPolicyEmpty = MutableLiveData<Boolean>(true)
    val isMyInterastedPolicyEmpty: LiveData<Boolean>
        get() = _isMyInterastedPolicyEmpty

    fun postBtnEnable(isEnable: Boolean) {
        _isQuestionValid.value = isEnable
    }

    fun logoutWithKakao() {
        viewModelScope.launch {
            authRepository.logoutWithKakao(UserData.accessToken!!)
        }
    }

    fun getMyPost() {
        viewModelScope.launch {
            _myPostList.value = userRepository.getUserPost(UserData.accessToken!!, UserData.platform!!, UserData.id!!).data.post
            _isMyPostEmpty.value = false
        }
    }

    fun getMyComment() {
        viewModelScope.launch {
            _myCommentList.value = userRepository.getUserComment(UserData.accessToken!!, UserData.platform!!, UserData.id!!).data.comment
            _isMyCommentEmpty.value = false
        }
    }

    fun getMyInterastedPolicy() {
        viewModelScope.launch {
            _myInterastedtPoLicyList.value = userRepository.getUserLikePolicy(UserData.accessToken!!, UserData.platform!!, UserData.id!!).data.policy
        }
    }
}