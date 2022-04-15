package com.fork.spoonfeed.presentation.ui.communitypost.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.local.dao.CommentReportDao
import com.fork.spoonfeed.data.remote.model.community.RequestCommentData
import com.fork.spoonfeed.data.remote.model.community.RequestDeleteCommentData
import com.fork.spoonfeed.data.remote.model.community.RequestPatchCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserData
import com.fork.spoonfeed.domain.repository.CommentRepository
import com.fork.spoonfeed.domain.repository.PostRepository
import com.fork.spoonfeed.domain.repository.UserRepository
import com.fork.spoonfeed.presentation.ui.community.view.CommunityFragment
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostActivity.Companion.REPORT_COMMENT
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostActivity.Companion.REPORT_POST
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityPostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    private val commentReportDao: CommentReportDao
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

    private val _isReportSuccess = MutableLiveData<Pair<Boolean, String>>()
    val isReportSuccess: LiveData<Pair<Boolean, String>> = _isReportSuccess

    fun initUserData() {
        viewModelScope.launch {
            _userData.value = userRepository.getUserData().data.user
        }
    }

    fun initData() {
        viewModelScope.launch {
            if (pk != null) {
                val reportedCommentAllData =
                    commentReportDao.getAllReportedComment().map { it.commentPk }
                postRepository.getPostDetail(pk).let { postData ->
                    _postDetailData.value = postData.data.post
                    _postCommentData.value = postData.data.comment.filterNot { commentData ->
                        reportedCommentAllData.contains(commentData.id)
                    }
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

    fun reportUser(isPostWriterReport: Boolean, commentId: Int?) {
        when (isPostWriterReport) {
            true -> {
                postWriterReport()
            }
            false -> {
                commentWriterReport(commentId)
            }
        }
    }

    private fun postWriterReport() {
        val postWriterId = _postDetailData.value?.authorId ?: return
        viewModelScope.launch {
            kotlin.runCatching {
                userRepository.blockUser(postWriterId)
            }.onSuccess {
                _isReportSuccess.value = true to REPORT_POST
            }.onFailure {
                _isReportSuccess.value = false to REPORT_POST
            }
        }
    }

    private fun commentWriterReport(commentId: Int?) {
        val commenterId = _postCommentData.value?.find { it.id == commentId }?.commenterId ?: return
        viewModelScope.launch {
            kotlin.runCatching {
                userRepository.blockUser(commenterId)
            }.onSuccess {
                _isReportSuccess.value = if (commenterId == _postDetailData.value?.authorId) {
                    true to REPORT_POST
                } else {
                    true to REPORT_COMMENT
                }
            }.onFailure {
                _isReportSuccess.value = false to REPORT_COMMENT
            }
        }
    }
}
