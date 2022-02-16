package com.fork.spoonfeed.presentation.ui.policylist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PolicyListViewModel : ViewModel() {


    private val _isFilterClicked = MutableLiveData<Boolean>(false)
    val isFilterClicked: LiveData<Boolean>
        get() = _isFilterClicked

    private val _isReWriteClicked = MutableLiveData<Boolean>()
    val isReWriteClicked: LiveData<Boolean>
        get() = _isReWriteClicked

    private val _selectedFileter = MutableLiveData(NOTHING)
    val selectedFileter: LiveData<Int>
        get() = _selectedFileter

    var initSelectedFilter = ALL

    fun allSelected() {
        _selectedFileter.value = ALL
    }

    fun dwellingSelected() {
        _selectedFileter.value = DWELLING
    }

    fun financeSelected() {
        _selectedFileter.value = FINANCE
    }

    fun nothingSelected() {
        _selectedFileter.value = NOTHING
    }

    fun applyFilter() {

    }

    fun filterOnClick() {
        _isFilterClicked.value = true
    }

    fun filterOnClickFalse() {
        _isFilterClicked.value = false
    }

    //다시입력 클릭 감지
    fun reWriteOnClick() {
        _isReWriteClicked.value = true
    }

    fun reWriteOnClickFalse() {
        _isReWriteClicked.value = false
    }


    companion object {
        const val NOTHING = 0
        const val ALL = 1
        const val DWELLING = 2
        const val FINANCE = 3
    }
}