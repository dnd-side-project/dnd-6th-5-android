package com.fork.spoonfeed.presentation.ui.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.local.AutoLoginManager
import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithKakaoData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithNaverData
import com.fork.spoonfeed.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _autoLogin = MutableLiveData<AutoLoginManager.Companion.UserInfo?>()
    val autoLogin: LiveData<AutoLoginManager.Companion.UserInfo?> = _autoLogin

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val _isTokenValid = MutableLiveData<Boolean>()
    val isTokenInvalid: LiveData<Boolean> = _isTokenValid

    fun autoLogin() {
        val userLoginData = authRepository.getAutoLoginManager()
        userLoginData?.let { userData ->
            _autoLogin.value = userData
            when (userData.platform) {
                "naver" -> {
                    loginWithNaver(userData)
                }
                "kakao" -> {
                    loginWithKakao(userData)
                }
            }
        } ?: run {
            _loginSuccess.value = false
        }
    }

    private fun loginWithNaver(userData: AutoLoginManager.Companion.UserInfo) {
        viewModelScope.launch {
            runCatching {
                authRepository.loginWithNaver(userData.accessToken, userData.refreshToken)
            }.onSuccess {
                loginSuccess(it, "naver")
            }.onFailure {
                if (_isTokenValid.value != null) {
                    _loginSuccess.value = false
                } else {
                    checkToken(userData.refreshToken, userData.platform)
                }
            }
        }
    }

    fun loginWithKakao(userData: AutoLoginManager.Companion.UserInfo) {
        viewModelScope.launch {
            runCatching {
                authRepository.loginWithKakao(userData.accessToken, userData.refreshToken)
            }.onSuccess {
                loginSuccess(it, "kakao")
            }.onFailure {
                if (_isTokenValid.value != null) {
                    _loginSuccess.value = false
                } else {
                    checkToken(userData.refreshToken, userData.platform)
                }
            }
        }
    }

    private fun loginSuccess(
        loginData: Pair<String, Any>,
        platform: String
    ) {
        val newAccessToken = loginData.first

        UserData.accessToken = newAccessToken
        UserData.platform = platform

        (loginData.second as? ResponseLoginWithNaverData)?.let {
            UserData.id = it.data.user.id
            UserData.refreshToken = it.data.user.token.refreshToken
            _loginSuccess.value = it.success
        } ?: run {
            (loginData.second as? ResponseLoginWithKakaoData)?.let {
                UserData.id = it.data.user.id
                UserData.refreshToken = it.data.user.token.refreshToken
                _loginSuccess.value = it.success
            }
        }
    }

    private fun checkToken(refreshToken: String, platform: String) {
        viewModelScope.launch {
            runCatching {
                authRepository.getToken(refreshToken, platform)
            }.onSuccess {
                val access = it.first ?: run {
                    _isTokenValid.value = false
                    return@launch
                }
                val refresh = it.second ?: run {
                    _isTokenValid.value = false
                    return@launch
                }
                _isTokenValid.value = true
                _autoLogin.value =
                    _autoLogin.value?.copy(accessToken = access, refreshToken = refresh)
                autoLogin()
            }.onFailure {
                _isTokenValid.value = false
                _loginSuccess.value = false
            }
        }
    }
}
