package com.fork.spoonfeed.presentation.ui.communitypost.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.community.RequestPatchPostData
import com.fork.spoonfeed.data.remote.model.community.RequestSendPostData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserData
import com.fork.spoonfeed.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityPostCreateViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _category = MutableLiveData<String>()
    val category: LiveData<String> = _category

    private val _title = MutableLiveData<String?>()
    val title: LiveData<String?> = _title

    private val _content = MutableLiveData<String?>()
    val content: LiveData<String?> = _content

    private var _userInfo: ResponseUserData.Data.User? = null

    private val _isValid = MutableLiveData(false)
    var isValid: LiveData<Boolean> = _isValid

    private val _sendSuccess = MutableLiveData(false)
    val sendSuccess: LiveData<Boolean> = _sendSuccess

    private val _patchSuccess = MutableLiveData(false)
    val patchSuccess: LiveData<Boolean> = _patchSuccess

    private val _postDetailData = MutableLiveData<ResponsePostData.Data.Post>()
    val postDetailData: LiveData<ResponsePostData.Data.Post> = _postDetailData

    fun setCategory(data: String) {
        _category.value = data
    }

    fun setTitle(data: String?) {
        _title.value = data
    }

    fun setContent(data: String?) {
        _content.value = data
    }

    fun isValid() {
        _isValid.value = _category.value != null && _title.value != null && _content.value != null
                && _userInfo != null
    }

    fun setUserInfo(data: ResponseUserData.Data.User) {
        _userInfo = data
    }

    fun sendPost() {
        val category = _category.value ?: return
        val title = _title.value ?: return
        val content = _content.value ?: return
        val userInfo = _userInfo ?: return

        val responseUserData = RequestSendPostData(
            userId = UserData.id!!,
            title = title,
            category = category,
            content = content,
            age = formattedAge(userInfo.age!!),
            maritalStatus = userInfo.maritalStatus,
            workStatus = userInfo.workStatus,
            companyScale = userInfo.companyScale,
            medianIncome = userInfo.medianIncome,
            annualIncome = userInfo.annualIncome,
            asset = userInfo.asset,
            isHouseOwner = userInfo.isHouseOwner,
            hasHouse = userInfo.hasHouse
        )

        viewModelScope.launch {
            _sendSuccess.value = postRepository.sendPost(responseUserData).success
        }
    }

    private fun formattedAge(age: String): String {
        return age.substring(0..3) + age.substring(5..6) + age.substring(8..9)
    }

    fun initData(pk: Int) {
        viewModelScope.launch {
            postRepository.getPostDetail(pk).let {
                _postDetailData.value = it.data.post
            }
        }
    }

    fun editPost(postId: Int) {

        val category = _category.value ?: return
        val title = _title.value ?: return
        val content = _content.value ?: return
        val userInfo = _userInfo ?: return

        val requestPatchPostData = RequestPatchPostData(
            userId = UserData.id!!,
            title = title,
            category = category,
            content = content,
            age = formattedAge(userInfo.age!!),
            maritalStatus = userInfo.maritalStatus,
            workStatus = userInfo.workStatus,
            companyScale = userInfo.companyScale,
            medianIncome = userInfo.medianIncome,
            annualIncome = userInfo.annualIncome,
            asset = userInfo.asset,
            isHouseOwner = userInfo.isHouseOwner,
            hasHouse = userInfo.hasHouse
        )
        viewModelScope.launch {
            _patchSuccess.value = postRepository.patchPost(postId,requestPatchPostData).success
        }
    }
}
