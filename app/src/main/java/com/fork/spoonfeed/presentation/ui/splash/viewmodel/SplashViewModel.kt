package com.fork.spoonfeed.presentation.ui.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _autoLoginPlatform = MutableLiveData<String?>()
    val autoLoginPlatform: LiveData<String?> = _autoLoginPlatform

    private val _naverLoginSuccessWithName = MutableLiveData<Pair<Boolean, String?>>()
    val naverLoginSuccessWithName: LiveData<Pair<Boolean, String?>> = _naverLoginSuccessWithName

    private val _kakaoLoginSuccessWithName = MutableLiveData<Pair<Boolean, String?>>()
    val kakaoLoginSuccessWithName: LiveData<Pair<Boolean, String?>> = _kakaoLoginSuccessWithName

    fun loginWithNaver(accessToken: String?, refreshToken: String?) {
        if (accessToken == null || refreshToken == null){
            _naverLoginSuccessWithName.value = false to null
        }

        val access = accessToken ?: return
        val refresh = refreshToken ?: return

        viewModelScope.launch {
            _naverLoginSuccessWithName.value =
                authRepository.loginWithNaver(access, refresh).run {
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

    fun getAutoLoginPlatform() {
        _autoLoginPlatform.value = authRepository.getAutoLoginPlatformManager()
    }

    fun loginWithKakao(accessToken: String?, refreshToken: String?) {
        if (accessToken == null || refreshToken == null){
            _naverLoginSuccessWithName.value = false to null
        }

        val access = accessToken ?: return
        val refresh = refreshToken ?: return

        viewModelScope.launch {
            val responseData = authRepository.loginWithKakao(access, refresh)
            _kakaoLoginSuccessWithName.value =
                responseData.success to responseData.data.user.nickname
            UserData.id = responseData.data.user.id
            UserData.refreshToken = responseData.data.user.token.refreshToken
            UserData.accessToken = accessToken
            UserData.platform = "kakao"
        }
    }
}
