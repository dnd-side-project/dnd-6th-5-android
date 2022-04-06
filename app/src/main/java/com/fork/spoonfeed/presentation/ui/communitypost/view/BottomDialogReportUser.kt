package com.fork.spoonfeed.presentation.ui.communitypost.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentBottomDialogReportUserBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomDialogReportUser(private val postPk: Int) :
    BaseViewUtil.BaseCategoryBottomDialogFragment<FragmentBottomDialogReportUserBinding>(R.layout.fragment_bottom_dialog_report_user) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        setReportClickListner()

    }

    private fun setHandler() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ dialog?.dismiss() }, 140)
    }

    private fun setReportClickListner() {
        binding.tvBottomDialogReportUser.setOnClickListener {
            startActivity(
                Intent(requireContext(), UserReportReasonActivity::class.java)
                    .putExtra("postPk", postPk)
            )
            setHandler()
        }
    }
}