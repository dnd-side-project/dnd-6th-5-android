package com.fork.spoonfeed.presentation.ui.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.remote.model.user.ResponseUserData
import com.fork.spoonfeed.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageMyInfoViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val _initialUserData = MutableLiveData<ResponseUserData.Data.User>()
    val initialUserInfo: LiveData<ResponseUserData.Data.User> = _initialUserData

    var updatedUserData: ResponseUserData.Data.User? = null

    fun getUserData(){
        viewModelScope.launch {
            userRepository.getUserData().data.user.let {
                _initialUserData.value = it
                updatedUserData = it
            }
        }
    }

    fun updateMedianIncome(data: String){
        updatedUserData = updatedUserData?.copy(
            medianIncome = data
        )
    }

    fun updateAnnualIncome(data: String){
        updatedUserData = updatedUserData?.copy(
            annualIncome = data
        )
    }

    fun updateAsset(data: String){
        updatedUserData = updatedUserData?.copy(
            asset = data
        )
    }

    fun updateHouseOwner(data: String){
        updatedUserData = updatedUserData?.copy(
            isHouseOwner = data
        )
    }

    fun updateHasHouse(data: String){
        updatedUserData = updatedUserData?.copy(
            hasHouse = data
        )
    }
}