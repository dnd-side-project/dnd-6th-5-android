package com.fork.spoonfeed.presentation.ui.communitypost.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fork.spoonfeed.data.local.dao.CommentReportDao
import com.fork.spoonfeed.data.local.dao.PostReportDao
import com.fork.spoonfeed.data.local.entity.CommentReportData
import com.fork.spoonfeed.data.local.entity.PostReportData
import com.fork.spoonfeed.data.remote.model.community.RequestPostReportData
import com.fork.spoonfeed.domain.repository.CommentRepository
import com.fork.spoonfeed.domain.repository.PostRepository
import com.fork.spoonfeed.presentation.ui.communitypost.view.UserReportReasonActivity
import com.fork.spoonfeed.presentation.ui.communitypost.view.UserReportReasonActivity.Companion.USER_REPORT_TYPE_COMMENT
import com.fork.spoonfeed.presentation.ui.communitypost.view.UserReportReasonActivity.Companion.USER_REPORT_TYPE_POST
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val postReportDao: PostReportDao,
    private val commentReportDao: CommentReportDao
) : ViewModel() {

    private val _reportedPostPk = savedStateHandle.get<Int>("postPk")
    private val _reportedCommentPk = savedStateHandle.get<Int>("commentPk")

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

    // success, report type
    private val _isReportSuccess = MutableLiveData<Pair<Boolean, String>>()
    val isReportSuccess: LiveData<Pair<Boolean, String>> = _isReportSuccess

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

    fun userReport() {
        var reportReason = ""

        if (_reportReasonOneCheck.value == true) reportReason =
            UserReportReasonActivity.REPORT_REASON_ONE
        if (_reportReasonTwoCheck.value == true) reportReason =
            UserReportReasonActivity.REPORT_REASON_TWO
        if (_reportReasonThreeCheck.value == true) reportReason =
            UserReportReasonActivity.REPORT_REASON_THREE
        if (_reportReasonFourCheck.value == true) reportReason =
            UserReportReasonActivity.REPORT_REASON_FOUR
        if (_reportReasonFiveCheck.value == true) reportReason =
            UserReportReasonActivity.REPORT_REASON_FIVE
        if (_reportReasonSixCheck.value == true) reportReason =
            UserReportReasonActivity.REPORT_REASON_SIX

        _reportedPostPk?.let {
            reportPost(it, reportReason)
        } ?: _reportedCommentPk?.let {
            reportComment(it, reportReason)
        }
    }

    private fun reportPost(postPk: Int, reportReason: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                postRepository.postReport(postPk, RequestPostReportData(reason = reportReason))
            }.onSuccess {
                _isReportSuccess.value = true to USER_REPORT_TYPE_POST
                insertReportedPostPk()
            }.onFailure {
                _isReportSuccess.value = false to USER_REPORT_TYPE_POST
            }
        }
    }

    private fun reportComment(commentPk: Int, reportReason: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                commentRepository.reportComment(
                    commentPk,
                    RequestPostReportData(reason = reportReason)
                )
            }.onSuccess {
                _isReportSuccess.value = true to USER_REPORT_TYPE_COMMENT
                insertReportedCommentPk()
            }.onFailure {
                _isReportSuccess.value = false to USER_REPORT_TYPE_COMMENT
            }
        }
    }

    private fun insertReportedPostPk() {
        val reportedPostPk = _reportedPostPk ?: return
        viewModelScope.launch {
            postReportDao.insertReportedPost(PostReportData(postPk = reportedPostPk))
        }
    }

    private fun insertReportedCommentPk() {
        val reportedCommentPk = _reportedCommentPk ?: return
        viewModelScope.launch {
            commentReportDao.insertReportedComment(CommentReportData(commentPk = reportedCommentPk))
        }
    }
}
