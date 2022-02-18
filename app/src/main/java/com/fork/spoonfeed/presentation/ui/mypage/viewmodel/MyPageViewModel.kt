package com.fork.spoonfeed.presentation.ui.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyPageViewModel : ViewModel() {
    private val _isQuestionValid = MutableLiveData(false)
    val isQuestionValid: LiveData<Boolean>
        get() = _isQuestionValid

    private val _email = MutableLiveData<String>()
    val email: LiveData<String>

        get() = _email

    private val _sentence = MutableLiveData<String>()
    val sentence: LiveData<String>
        get() = _sentence

    fun postBtnEnable(isEnable: Boolean) {
        _isQuestionValid.value = isEnable
    }
}