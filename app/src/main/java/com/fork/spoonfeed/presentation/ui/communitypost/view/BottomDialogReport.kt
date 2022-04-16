package com.fork.spoonfeed.presentation.ui.communitypost.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentBottomDialogReportUserBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.communitypost.viewmodel.CommunityPostViewModel
import com.fork.spoonfeed.presentation.util.showFloatingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomDialogReport :
    BaseViewUtil.BaseCategoryBottomDialogFragment<FragmentBottomDialogReportUserBinding>(R.layout.fragment_bottom_dialog_report_user) {

    private val communityPostViewModel: CommunityPostViewModel by activityViewModels()

    private var postPk: Int? = null
    private var commentPk: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        arguments?.getInt(CommunityPostActivity.REPORT_POST_PK).let {
            postPk = if (it == 0) null else it
        }
        arguments?.getInt(CommunityPostActivity.REPORT_COMMENT_PK).let {
            commentPk = if (it == 0) null else it
        }
        setReportClickListener()
        setObserver()
    }

    private fun setReportClickListener() {
        binding.tvBottomDialogReportContent.setOnClickListener {
            startActivity(
                Intent(requireContext(), UserReportReasonActivity::class.java).apply {
                    postPk?.let {
                        putExtra("postPk", postPk)
                    }
                    commentPk?.let {
                        putExtra("commentPk", commentPk)
                    }
                }
            )
            setHandler()
        }
        binding.tvBottomDialogReportUser.setOnClickListener {
            setHandler()
            showUserReportDialog()
        }
        binding.tvBottomDialogReportClose.setOnClickListener {
            setHandler()
        }
    }

    private fun setHandler() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ dialog?.dismiss() }, 140)
    }

    private fun setObserver() {
        communityPostViewModel.isReportSuccess.observe(this) {
            if (it.first) {
                setHandler()
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun showUserReportDialog() {
        val dialog = requireContext().showFloatingDialog(R.layout.dialog_user_report)
        val confirmBtn = dialog.findViewById<Button>(R.id.btn_user_report_confirm)
        val cancelBtn = dialog.findViewById<Button>(R.id.btn_user_report_cancel)

        confirmBtn.setOnClickListener {
            communityPostViewModel.reportUser(postPk != null, commentPk)
            dialog.dismiss()
        }

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
    }
}
