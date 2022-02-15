package com.fork.spoonfeed.presentation.ui.policylist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailInfoViewModel : ViewModel() {

    private val _isLikeClicked = MutableLiveData(false)
    val isLikeClicked: LiveData<Boolean>
        get() = _isLikeClicked
}