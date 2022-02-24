package com.fork.spoonfeed.presentation.ui.communitypost.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData
import com.fork.spoonfeed.domain.repository.PostRepository
import com.fork.spoonfeed.presentation.ui.community.view.CommunityFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityPostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val postRepository: PostRepository
) : ViewModel() {

    private val pk = savedStateHandle.get<Int>(CommunityFragment.POST_PK)

    private val _postDetailData = MutableLiveData<ResponsePostData.Data.Post>()
    val postDetailData: LiveData<ResponsePostData.Data.Post> = _postDetailData

    private val _postCommentData = MutableLiveData<List<ResponsePostData.Data.Comment>>()
    val postCommentData: LiveData<List<ResponsePostData.Data.Comment>> = _postCommentData

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
}
