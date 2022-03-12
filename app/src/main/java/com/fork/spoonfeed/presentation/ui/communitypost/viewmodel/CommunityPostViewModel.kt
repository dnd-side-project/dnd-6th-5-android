package com.fork.spoonfeed.presentation.ui.communitypost.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.community.RequestCommentData
import com.fork.spoonfeed.data.remote.model.community.RequestDeleteCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData
import com.fork.spoonfeed.domain.repository.CommentRepository
import com.fork.spoonfeed.domain.repository.PostRepository
import com.fork.spoonfeed.presentation.ui.community.view.CommunityFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityPostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository
) : ViewModel() {

    private val pk = savedStateHandle.get<Int>(CommunityFragment.POST_PK)

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

    private val _deleteCommentSuccess = MutableLiveData(false)
    val deleteCommentSuccess: LiveData<Boolean> = _deleteCommentSuccess

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

    fun postComment() {
        val input = _commentInput.value ?: return

        viewModelScope.launch {
            if (pk != null) {
                _isCommentPostSuccess.value = commentRepository.postComment(
                    pk,
                    RequestCommentData(UserData.id!!, input)
                ).success
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

    fun deleteComment(comment: ResponsePostData.Data.Comment) {
        val requestDeleteCommentData = RequestDeleteCommentData(comment.id)
        viewModelScope.launch {
            _deleteCommentSuccess.value =
                pk?.let { commentRepository.deleteComment(it, requestDeleteCommentData).success }
        }
    }
}
