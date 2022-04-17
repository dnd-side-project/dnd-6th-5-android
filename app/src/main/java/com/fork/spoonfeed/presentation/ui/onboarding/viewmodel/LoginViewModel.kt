package com.fork.spoonfeed.presentation.ui.onboarding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.local.AutoLoginManager
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

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val _name = MutableLiveData<String?>()
    val name: LiveData<String?> = _name

    private val _email = MutableLiveData<String?>()
    val email: LiveData<String?> = _email

    private val _isNameSpecial = MutableLiveData<Boolean>()
    val isNameSpecial: LiveData<Boolean> = _isNameSpecial

    private val _nickNameSetStatus = MutableLiveData(false)
    val nickNameSetStatus: LiveData<Boolean> get() = _nickNameSetStatus

    fun setEmail(email: String) {
        _email.value = email
    }

    fun loginWithNaver(accessToken: String, refreshToken: String) {
        val email = _email.value ?: return
        viewModelScope.launch {
            _loginSuccess.value =
                authRepository.loginWithNaver(accessToken, refreshToken).run {
                    val newAccessToken = first
                    val responseBody = second

                    UserData.id = responseBody.data.user.id
                    UserData.accessToken = newAccessToken
                    UserData.refreshToken = responseBody.data.user.token.refreshToken
                    UserData.platform = "naver"
                    UserData.email = email

                    authRepository.setAutoLoginManager(
                        AutoLoginManager.Companion.UserInfo(
                            refreshToken = responseBody.data.user.token.refreshToken,
                            accessToken = newAccessToken,
                            platform = "naver",
                            email = email
                        )
                    )

                    if (responseBody.data.user.nickname == null) {
                        false
                    } else {
                        responseBody.success
                    }
                }
        }
    }

    fun loginWithKakao(accessToken: String, refreshToken: String) {
        val email = _email.value ?: return

        viewModelScope.launch {
            _loginSuccess.value =
                authRepository.loginWithKakao(accessToken, refreshToken).run {
                    val newAccessToken = first
                    val responseBody = second

                    UserData.id = responseBody.data.user.id
                    UserData.accessToken = newAccessToken
                    UserData.refreshToken = responseBody.data.user.token.refreshToken
                    UserData.platform = "kakao"
                    UserData.email = email

                    authRepository.setAutoLoginManager(
                        AutoLoginManager.Companion.UserInfo(
                            refreshToken = responseBody.data.user.token.refreshToken,
                            accessToken = newAccessToken,
                            platform = "kakao",
                            email = email
                        )
                    )

                    if (responseBody.data.user.nickname == null) {
                        false
                    } else {
                        responseBody.success
                    }
                }
        }
    }

    fun setUserNickName(userNickName: String) {
        _name.value = userNickName
        checkUserNickName(userNickName)
    }

    private fun checkUserNickName(userNickName: String) {
        viewModelScope.launch {
            _isNameSpecial.value = userRepository.checkUserNameDuplicate(userNickName).success
        }
    }

    fun patchUserNickName() {
        val userNickName = _name.value ?: return
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
                authRepository.setAutoLoginManager(
                    AutoLoginManager.Companion.UserInfo(
                        refreshToken = UserData.refreshToken!!,
                        accessToken = UserData.accessToken!!,
                        platform = UserData.platform!!,
                        email = UserData.email!!
                    )
                )
                _nickNameSetStatus.value = true
            }.onFailure {
                _nickNameSetStatus.value = false
            }
        }
    }
}
