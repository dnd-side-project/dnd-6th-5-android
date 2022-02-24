package com.fork.spoonfeed.presentation.ui.mypage.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivitySecessionBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.util.setBackBtnClickListener
import com.fork.spoonfeed.presentation.util.showFloatingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecessionActivity : BaseViewUtil.BaseAppCompatActivity<ActivitySecessionBinding>(R.layout.activity_secession) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        this.setBackBtnClickListener(binding.ivSecessionBack)
        secessionClickListener()
    }

    private fun secessionClickListener() {
        binding.btnSecession.setOnClickListener {
            showSecessionDoneDialog()
        }
    }

    @SuppressLint("ResourceType")
    private fun showSecessionDoneDialog() {
        val dialog = this.showFloatingDialog(R.layout.dialog_secession)
        val confirmBtn = dialog.findViewById<Button>(R.id.tv_secession_dialog_confirm)
        val cancelBtn = dialog.findViewById<Button>(R.id.tv_secession_dialog_cancel)

        confirmBtn.setOnClickListener {
            dialog.dismiss()
            finish()
        }

        cancelBtn.setOnClickListener {
            dialog.dismiss()
            finish()
        }
    }
}