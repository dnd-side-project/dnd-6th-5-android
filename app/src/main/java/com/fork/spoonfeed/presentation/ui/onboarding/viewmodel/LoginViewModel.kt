package com.fork.spoonfeed.presentation.ui.onboarding.viewmodel

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

    private val _isNaverLoginSuccess = MutableLiveData(false)
    val isNaverLoginSuccess: LiveData<Boolean> = _isNaverLoginSuccess

    private val _isKakaoLoginSuccess = MutableLiveData(false)
    val isKakaoLoginSuccess: LiveData<Boolean> = _isKakaoLoginSuccess

    var nickNameText = MutableLiveData<String>()

    private val _nickNameSetStatus = MutableLiveData(false)
    val nickNameSetStatus: LiveData<Boolean> get() = _nickNameSetStatus

    fun loginWithNaver(accessToken: String, refreshToken: String) {
        viewModelScope.launch {
            _isNaverLoginSuccess.value =
                authRepository.loginWithNaver(accessToken, refreshToken).run {
                    val newAccessToken = first
                    val responseBody = second

                    UserData.id = responseBody.data.user.id
                    UserData.accessToken = newAccessToken
                    UserData.refreshToken = responseBody.data.user.token.refreshToken
                    UserData.platform = "naver"

                    responseBody.success
                }
        }
    }

    fun loginWithKakao(accessToken: String, refreshToken: String) {
        viewModelScope.launch {
            _isKakaoLoginSuccess.value =
                authRepository.loginWithKakao(accessToken, refreshToken).run {
                    UserData.id = data.user.id
                    UserData.refreshToken = data.user.token.refreshToken
                    UserData.accessToken = accessToken
                    UserData.platform = "kakao"

                    success
                }
        }
    }

    fun patchUserNickName() {

        viewModelScope.launch {
            kotlin.runCatching {
                var requestUserNickNameData = RequestUserNickNameData(UserData.id.toString(), nickNameText.value.toString())
                userRepository.patchUserNickName(UserData.accessToken!!, UserData.platform!!, requestUserNickNameData)
            }.onSuccess {
                _nickNameSetStatus.setValue(true)
            }.onFailure {
                _nickNameSetStatus.setValue(false)
            }
        }
    }
}
