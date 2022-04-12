package com.fork.spoonfeed.presentation.ui.community.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.local.dao.PostReportDao
import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData
import com.fork.spoonfeed.domain.repository.PostRepository
import com.fork.spoonfeed.presentation.base.BaseViewUtil.BaseCategoryBottomDialogFragment.Companion.ALL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class User(val name: String, val age: Int)

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val dataBase: PostReportDao
) : ViewModel() {

    private val _postData = MutableLiveData<List<ResponsePostAllData.Data.Post>>()
    val postData: LiveData<List<ResponsePostAllData.Data.Post>> = _postData

    private val _filteredPostData = MutableLiveData<List<ResponsePostAllData.Data.Post>>()
    val filteredPostData: LiveData<List<ResponsePostAllData.Data.Post>> = _filteredPostData

    private val _isFilterClicked = MutableLiveData<Boolean>(false)
    val isFilterClicked: LiveData<Boolean>
        get() = _isFilterClicked

    private val _selectedFilter = MutableLiveData(ALL)
    val selectedFilter: LiveData<String>
        get() = _selectedFilter

    fun getPostData() {
        val filter = _selectedFilter.value ?: return
        viewModelScope.launch {

            _postData.value = if (filter == ALL) {
                postRepository.getPostAll().data.post
            } else {
                postRepository.getPostAll().data.post.filter { it.category == _selectedFilter.value }
            }

            val reportedPostPkList = dataBase.getAllReportedPost()?.map { it.postPk }
            if (reportedPostPkList != null) {
                _filteredPostData.value = _postData.value?.filterNot { reportedPostPkList.contains(it.id) }?.map {
                    ResponsePostAllData.Data.Post(
                        id = it.id,
                        author = it.author,
                        title = it.title,
                        category = it.category,
                        content = it.content,
                        commentCount = it.commentCount,
                        createdAt = it.createdAt,
                        updatedAt = it.updatedAt,
                    )
                }
            }
        }
    }

    fun setCategorySelected(category: String) {
        _selectedFilter.value = category
    }

    fun filterOnClick() {
        _isFilterClicked.value = true
    }

    fun filterOnClickFalse() {
        _isFilterClicked.value = false
    }
}
