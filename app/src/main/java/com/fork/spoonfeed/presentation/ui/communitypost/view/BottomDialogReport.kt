package com.fork.spoonfeed.presentation.ui.communitypost.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentBottomDialogReportUserBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class BottomDialogReport :
    BaseViewUtil.BaseCategoryBottomDialogFragment<FragmentBottomDialogReportUserBinding>(R.layout.fragment_bottom_dialog_report_user) {

    private var postPk by Delegates.notNull<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        arguments?.getInt(CommunityPostActivity.REPORT_POST_PK)?.let {
            postPk = it
        }
        setReportClickListener()
    }

    private fun setHandler() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ dialog?.dismiss() }, 140)
    }

    private fun setReportClickListener() {
        binding.tvBottomDialogReportUser.setOnClickListener {
            startActivity(
                Intent(requireContext(), UserReportReasonActivity::class.java).apply {
                    putExtra("postPk", postPk)
                }
            )
            setHandler()
        }
    }
}
