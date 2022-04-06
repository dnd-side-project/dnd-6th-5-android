package com.fork.spoonfeed.presentation.ui.communitypost.viewmodel

import androidx.lifecycle.*
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.community.RequestCommentData
import com.fork.spoonfeed.data.remote.model.community.RequestDeleteCommentData
import com.fork.spoonfeed.data.remote.model.community.RequestPatchCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserData
import com.fork.spoonfeed.domain.repository.CommentRepository
import com.fork.spoonfeed.domain.repository.PostRepository
import com.fork.spoonfeed.domain.repository.UserRepository
import com.fork.spoonfeed.presentation.ui.community.view.CommunityFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityPostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val pk = savedStateHandle.get<Int>(CommunityFragment.POST_PK)

    private val _userData = MutableLiveData<ResponseUserData.Data.User>()
    val userData: LiveData<ResponseUserData.Data.User> = _userData

    private val _commentInput = MutableLiveData<String?>()
    val commentInput: LiveData<String?> = _commentInput

    private val _isCommentPostSuccess = MutableLiveData(false)
    val isCommentPostSuccess: LiveData<Boolean> = _isCommentPostSuccess

    private val _postDetailData = MutableLiveData<ResponsePostData.Data.Post>()
    val postDetailData: LiveData<ResponsePostData.Data.Post> = _postDetailData

    private val _postCommentData = MutableLiveData<List<ResponsePostData.Data.Comment>>()
    val postCommentData: LiveData<List<ResponsePostData.Data.Comment>> = _postCommentData

    private val _deletePostSuccess = MutableLiveData(false)
    val deletePostSuccess: LiveData<Boolean> = _deletePostSuccess

    private val _updateComment = MutableLiveData<ResponsePostData.Data.Comment>()
    val updateComment: LiveData<ResponsePostData.Data.Comment> = _updateComment

    private val _deleteCommentSuccess = MutableLiveData(false)
    val deleteCommentSuccess: LiveData<Boolean> = _deleteCommentSuccess

    private val _reportReasonOneCheck = MutableLiveData<Boolean>()
    val reportReasonOneCheck: LiveData<Boolean> = _reportReasonOneCheck

    private val _reportReasonTwoCheck = MutableLiveData<Boolean>()
    val reportReasonTwoCheck: LiveData<Boolean> = _reportReasonTwoCheck

    private val _reportReasonThreeCheck = MutableLiveData<Boolean>()
    val reportReasonThreeCheck: LiveData<Boolean> = _reportReasonThreeCheck

    private val _reportReasonFourCheck = MutableLiveData<Boolean>()
    val reportReasonFourCheck: LiveData<Boolean> = _reportReasonFourCheck

    private val _reportReasonFiveCheck = MutableLiveData<Boolean>()
    val reportReasonFiveCheck: LiveData<Boolean> = _reportReasonFiveCheck

    private val _reportReasonSixCheck = MutableLiveData<Boolean>()
    val reportReasonSixCheck: LiveData<Boolean> = _reportReasonSixCheck

    private val _isReportReasonValid = MutableLiveData(false)
    val isReportReasonValid: LiveData<Boolean> = _isReportReasonValid

    fun initUserData() {
        viewModelScope.launch {
            _userData.value = userRepository.getUserData().data.user
        }
    }

    fun initData() {
        viewModelScope.launch {
            if (pk != null) {
                postRepository.getPostDetail(pk).let {
                    _postDetailData.value = it.data.post
                    _postCommentData.value = it.data.comment
                }
            }
        }
    }

    fun getUserData(): ResponseUserData.Data.User? {
        return _userData.value
    }

    fun postComment() {
        val input = _commentInput.value ?: return
        val updateData = _updateComment.value
        viewModelScope.launch {
            if (pk != null) {
                if (updateData != null) {
                    _isCommentPostSuccess.value = commentRepository.patchComment(
                        pk,
                        RequestPatchCommentData(updateData.id, input)
                    ).success
                } else {
                    _isCommentPostSuccess.value = commentRepository.postComment(
                        pk,
                        RequestCommentData(UserData.id!!, input)
                    ).success
                }
            }
        }
    }

    fun setCommentInput(data: String?) {
        _commentInput.value = data
    }

    fun getPk(): Int? {
        return pk
    }

    fun deletePost() {
        viewModelScope.launch {
            _deletePostSuccess.value = pk?.let { postRepository.deletePost(it).success }
        }
    }

    fun updateComment(comment: ResponsePostData.Data.Comment) {
        _updateComment.value = comment
    }

    fun deleteComment(comment: ResponsePostData.Data.Comment) {
        val requestDeleteCommentData = RequestDeleteCommentData(comment.id)
        viewModelScope.launch {
            _deleteCommentSuccess.value =
                pk?.let { commentRepository.deleteComment(it, requestDeleteCommentData).success }
        }
    }

    fun setReportReasonOneStatus(status: Boolean) {
        _reportReasonOneCheck.value = status
        if (status) {
            _reportReasonTwoCheck.value = false
            _reportReasonThreeCheck.value = false
            _reportReasonFourCheck.value = false
            _reportReasonFiveCheck.value = false
            _reportReasonSixCheck.value = false
        }
        setReportReasonValid()
    }

    fun setReportReasonTwoStatus(status: Boolean) {
        _reportReasonTwoCheck.value = status
        if (status) {
            _reportReasonOneCheck.value = false
            _reportReasonThreeCheck.value = false
            _reportReasonFourCheck.value = false
            _reportReasonFiveCheck.value = false
            _reportReasonSixCheck.value = false
        }
        setReportReasonValid()
    }

    fun setReportReasonThreeStatus(status: Boolean) {
        _reportReasonThreeCheck.value = status
        if (status) {
            _reportReasonTwoCheck.value = false
            _reportReasonOneCheck.value = false
            _reportReasonFourCheck.value = false
            _reportReasonFiveCheck.value = false
            _reportReasonSixCheck.value = false
        }
        setReportReasonValid()
    }

    fun setReportReasonFourStatus(status: Boolean) {
        _reportReasonFourCheck.value = status
        if (status) {
            _reportReasonTwoCheck.value = false
            _reportReasonThreeCheck.value = false
            _reportReasonOneCheck.value = false
            _reportReasonFiveCheck.value = false
            _reportReasonSixCheck.value = false
        }
        setReportReasonValid()
    }

    fun setReportReasonFiveStatus(status: Boolean) {
        _reportReasonFiveCheck.value = status
        if (status) {
            _reportReasonTwoCheck.value = false
            _reportReasonThreeCheck.value = false
            _reportReasonOneCheck.value = false
            _reportReasonFourCheck.value = false
            _reportReasonSixCheck.value = false
        }
        setReportReasonValid()
    }

    fun setReportReasonSixStatus(status: Boolean) {
        _reportReasonSixCheck.value = status
        if (status) {
            _reportReasonTwoCheck.value = false
            _reportReasonThreeCheck.value = false
            _reportReasonOneCheck.value = false
            _reportReasonFourCheck.value = false
            _reportReasonFiveCheck.value = false
        }
        setReportReasonValid()
    }


    private fun setReportReasonValid() {
        _isReportReasonValid.value =
            _reportReasonOneCheck.value == true || _reportReasonTwoCheck.value == true || _reportReasonThreeCheck.value == true ||
                    _reportReasonFourCheck.value == true || _reportReasonFiveCheck.value == true || _reportReasonSixCheck.value == true

    }
}
