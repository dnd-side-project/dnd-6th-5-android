package com.fork.spoonfeed.presentation.ui.community.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fork.spoonfeed.presentation.ui.policylist.view.BottomDialogFilterFragment.Companion.FINANCE
import com.fork.spoonfeed.presentation.ui.policylist.view.BottomDialogFilterFragment.Companion.ALL
import com.fork.spoonfeed.presentation.ui.policylist.view.BottomDialogFilterFragment.Companion.DWELLING

class CommunityViewModel : ViewModel() {

    private val _isFilterClicked = MutableLiveData<Boolean>(false)
    val isFilterClicked: LiveData<Boolean>
        get() = _isFilterClicked


    private val _selectedFilter = MutableLiveData(ALL)
    val selectedFilter: LiveData<String>
        get() = _selectedFilter

    var initSelectedFilter = ALL

    fun allSelected() {
        _selectedFilter.value = ALL
    }

    fun dwellingSelected() {
        _selectedFilter.value = DWELLING
    }

    fun financeSelected() {
        _selectedFilter.value = FINANCE
    }

    fun filterOnClick() {
        _isFilterClicked.value = true
    }

    fun filterOnClickFalse() {
        _isFilterClicked.value = false
    }

}
