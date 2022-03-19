package com.fork.spoonfeed.presentation.ui.onboarding.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.user.RequestUserNickNameData
import com.fork.spoonfeed.domain.repository.AuthRepository
import com.fork.spoonfeed.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _naverLoginSuccessWithName = MutableLiveData<Pair<Boolean, String?>>(false to null)
    val naverLoginSuccessWithName: LiveData<Pair<Boolean, String?>> = _naverLoginSuccessWithName

    private val _kakaoLoginSuccessWithName = MutableLiveData<Pair<Boolean, String?>>(false to null)
    val kakaoLoginSuccessWithName: LiveData<Pair<Boolean, String?>> = _kakaoLoginSuccessWithName

    private val _isNameSpecial = MutableLiveData<Boolean>()
    val isNameSpecial: LiveData<Boolean> = _isNameSpecial

    private val _nickNameSetStatus = MutableLiveData(false)
    val nickNameSetStatus: LiveData<Boolean> get() = _nickNameSetStatus

    fun loginWithNaver(accessToken: String, refreshToken: String) {
        viewModelScope.launch {
            _naverLoginSuccessWithName.value =
                authRepository.loginWithNaver(accessToken, refreshToken).run {
                    val newAccessToken = first
                    val responseBody = second

                    UserData.id = responseBody.data.user.id
                    UserData.accessToken = newAccessToken
                    UserData.refreshToken = responseBody.data.user.token.refreshToken
                    UserData.platform = "naver"

                    responseBody.success to responseBody.data.user.nickname
                }
        }
    }

    fun loginWithKakao(accessToken: String, refreshToken: String) {
        viewModelScope.launch {
            val responseData = authRepository.loginWithKakao(accessToken, refreshToken)
            _kakaoLoginSuccessWithName.value =
                responseData.success to responseData.data.user.nickname
            UserData.id = responseData.data.user.id
            UserData.refreshToken = responseData.data.user.token.refreshToken
            UserData.accessToken = accessToken
            UserData.platform = "kakao"
        }
    }

    fun setUserNickName(userNickName: String) {
        Log.i("name", userNickName)
        val isNameSpecial = _isNameSpecial.value ?: false
        if (isNameSpecial) {
            patchUserNickName(userNickName)
        } else {
            checkUserNickName(userNickName)
        }
    }

    private fun checkUserNickName(userNickName: String) {
        viewModelScope.launch {
            _isNameSpecial.value = userRepository.checkUserNameDuplicate(userNickName).success
        }
    }

    fun patchUserNickName(userNickName: String) {
        viewModelScope.launch {
            runCatching {
                val requestUserNickNameData =
                    RequestUserNickNameData(UserData.id.toString(), userNickName)
                userRepository.patchUserNickName(
                    UserData.accessToken!!,
                    UserData.platform!!,
                    requestUserNickNameData
                ).data.user.nickname
            }.onSuccess {
                _nickNameSetStatus.setValue(true)
            }.onFailure {
                _nickNameSetStatus.setValue(false)
            }
        }
    }
}
