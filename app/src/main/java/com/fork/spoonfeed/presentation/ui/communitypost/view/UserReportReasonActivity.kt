package com.fork.spoonfeed.presentation.ui.communitypost.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityUserReportReasonBinding
import com.fork.spoonfeed.presentation.MainActivity
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.communitypost.viewmodel.ReportViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserReportReasonActivity : BaseViewUtil.BaseAppCompatActivity<ActivityUserReportReasonBinding>(R.layout.activity_user_report_reason) {

    private val reportViewModel: ReportViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.userReportActivity = this
        binding.reportViewModel = reportViewModel
        initView()
    }

    override fun initView() {
        setObserve()
        setBackBtnClickListener()
    }

    private fun setObserve() {
        reportViewModel.isPostReportSuccess.observe(this) { isUserReportSuccess ->
            if (isUserReportSuccess) {
                Toast.makeText(this, "신고가 완료되었습니다.", Toast.LENGTH_LONG).show()
                moveToCommunityFragment()
            }
        }
    }

    private fun setBackBtnClickListener() {
        binding.mtUserReport.setNavigationOnClickListener {
            finish()
        }
    }

    private fun moveToCommunityFragment() {
        startActivity(Intent(baseContext, MainActivity::class.java).apply {
            putExtra(USER_REPORT_KEY, USER_REPORT)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }

    companion object {
        const val REPORT_REASON_ONE = "스팸광고/도배글"
        const val REPORT_REASON_TWO = "음란물/선정성 게시글"
        const val REPORT_REASON_THREE = "욕설 및 비속어, 혐오와 같은 불쾌한 표현"
        const val REPORT_REASON_FOUR = "개인정보 노출"
        const val REPORT_REASON_FIVE = "부적절한 닉네임"
        const val REPORT_REASON_SIX = "주제와 무관한 내용"
        const val USER_REPORT_KEY = "com.fork.spoonfeed.presentation.ui.communitypost.view USER_REPORT_KEY"
        const val USER_REPORT = "com.fork.spoonfeed.presentation.ui.communitypost.view USER_REPORT"
    }
}
