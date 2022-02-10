package com.fork.spoonfeed.presentation.ui.policylist

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityPolicyListBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.policylist.adapter.PolicyListAdapter
import com.fork.spoonfeed.presentation.ui.policylist.adapter.PolicyListResponseData

class PolicyListActivity : BaseViewUtil.BaseAppCompatActivity<ActivityPolicyListBinding>(R.layout.activity_policy_list) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        initAdapter(
            mutableListOf(
                PolicyListResponseData(1, "주거", "청년 우대 통장", "아이조아아이조아아이조아", "2022.02-0222.02", 2),
                PolicyListResponseData(1, "주거", "청년 우대 통장", "아이조아아이조아아이조아", "2022.02-0222.02", 2),
                PolicyListResponseData(1, "주거", "청년 우대 통장", "아이조아아이조아아이조아", "2022.02-0222.02", 2),
                PolicyListResponseData(1, "주거", "청년 우대 통장", "아이조아아이조아아이조아", "2022.02-0222.02", 2),
                PolicyListResponseData(1, "주거", "청년 우대 통장", "아이조아아이아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아", "2022.02-0222.02", 2),
                PolicyListResponseData(1, "주거", "청년 우대 통장", "아이조아아이조아아이조아", "2022.02-0222.02", 2),
                PolicyListResponseData(1, "주거", "청년 우대 통장", "아이조아아이조아아이조아", "2022.02-0222.02", 2)
            )
        )
        initClickListener()
    }

    private fun initAdapter(dataList: MutableList<PolicyListResponseData>) {
        val policyListAdapter = PolicyListAdapter(dataList) { item ->
            startActivity(Intent(this, DetailInfoActivity::class.java))
        }

        with(binding) {
            rvPolicylist.adapter = policyListAdapter
            binding.rvPolicylist.layoutManager = LinearLayoutManager(this@PolicyListActivity)
        }
    }

    private fun initClickListener() {
        binding.tvPolicylistRewrite.setOnClickListener {
            showReWritedialog()
        }
    }

    private fun showReWritedialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_policy_list_rewrite)
        dialog.setCancelable(true)

        dialog.getWindow()!!.setGravity(Gravity.CENTER)
        dialog.show()

        val confirmBtn = dialog.findViewById<Button>(R.id.tv_dialog_confirm)
        confirmBtn.setOnClickListener {
            dialog.dismiss()
        }
        val CancelBtn = dialog.findViewById<Button>(R.id.tv_dialog_cancel)
        CancelBtn.setOnClickListener {
            dialog.dismiss()
        }
    }
}