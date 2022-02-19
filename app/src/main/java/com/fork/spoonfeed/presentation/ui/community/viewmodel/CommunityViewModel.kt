package com.fork.spoonfeed.presentation.ui.community.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fork.spoonfeed.presentation.ui.policylist.viewmodel.PolicyListViewModel

class CommunityViewModel : ViewModel() {

    private val _isFilterClicked = MutableLiveData<Boolean>(false)
    val isFilterClicked: LiveData<Boolean>
        get() = _isFilterClicked


    private val _selectedFileter = MutableLiveData(PolicyListViewModel.NOTHING)
    val selectedFileter: LiveData<String>
        get() = _selectedFileter

    var initSelectedFilter = PolicyListViewModel.ALL

    fun allSelected() {
        _selectedFileter.value = PolicyListViewModel.ALL
    }

    fun dwellingSelected() {
        _selectedFileter.value = PolicyListViewModel.DWELLING
    }

    fun financeSelected() {
        _selectedFileter.value = PolicyListViewModel.FINANCE
    }

    fun nothingSelected() {
        _selectedFileter.value = PolicyListViewModel.NOTHING
    }

    fun filterOnClick() {
        _isFilterClicked.value = true
    }

    fun filterOnClickFalse() {
        _isFilterClicked.value = false
    }

}