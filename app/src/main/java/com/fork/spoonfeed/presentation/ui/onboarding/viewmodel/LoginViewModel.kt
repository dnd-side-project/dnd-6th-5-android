package com.fork.spoonfeed.presentation.ui.onboarding.viewmodel

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
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _isNaverLoginSuccess = MutableLiveData(false)
    val isNaverLoginSuccess: LiveData<Boolean> = _isNaverLoginSuccess

    fun loginWithNaver(accessToken: String, refreshToken: String) {
        viewModelScope.launch {
            _isNaverLoginSuccess.value =
                authRepository.loginWithNaver(accessToken, refreshToken).run {
                    UserData.id = data.user.id
                    UserData.refresh_token = data.user.token.refreshToken
                    UserData.platform = "naver"

                    success
                }
        }
    }
}