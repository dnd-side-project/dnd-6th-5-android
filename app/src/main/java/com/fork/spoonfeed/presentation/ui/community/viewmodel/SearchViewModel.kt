package com.fork.spoonfeed.presentation.ui.community.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData
import com.fork.spoonfeed.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> = _searchQuery

    private val _searchPostAllData = MutableLiveData<List<ResponsePostAllData.Data.Post>>()
    val searchPostAllData: LiveData<List<ResponsePostAllData.Data.Post>> = _searchPostAllData

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchPost() {
        val query = _searchQuery.value ?: return
        viewModelScope.launch {
            kotlin.runCatching {
                repository.searchPost(query).data.post
                    .map { data ->
                        ResponsePostAllData.Data.Post(
                            data.id,
                            data.author,
                            data.title,
                            data.category,
                            data.content,
                            data.commentCount,
                            data.createdAt,
                            data.updatedAt,
                        )
                    }
            }.onSuccess {
                _searchPostAllData.value = it
            }.onFailure {
                Log.d("error", it.message.toString())
            }
        }
    }
}
