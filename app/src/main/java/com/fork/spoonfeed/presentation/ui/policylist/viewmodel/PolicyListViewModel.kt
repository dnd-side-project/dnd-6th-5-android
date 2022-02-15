package com.fork.spoonfeed.presentation.ui.policylist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PolicyListViewModel : ViewModel() {


    private val _isFilterClicked = MutableLiveData<Boolean>()
    val isFilterClicked: LiveData<Boolean>
        get() = _isFilterClicked

    private val _isReWriteClicked = MutableLiveData<Boolean>()
    val isReWriteClicked: LiveData<Boolean>
        get() = _isReWriteClicked

    private var _selectedFileter = MutableLiveData(NOTHING)
    val selectedFileter: LiveData<Int>
        get() = _selectedFileter

    fun allSelected() {
        _selectedFileter.value = ALL
    }

    fun dwellingSelected() {
        _selectedFileter.value = DWELLING
    }

    fun financeSelected() {
        _selectedFileter.value = FINANCE
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