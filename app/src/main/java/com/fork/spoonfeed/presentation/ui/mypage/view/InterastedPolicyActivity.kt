package com.fork.spoonfeed.presentation.ui.mypage.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityInterastedPolicyBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.policylist.adapter.PolicyListAdapter
import com.fork.spoonfeed.presentation.ui.policylist.adapter.PolicyListResponseData
import com.fork.spoonfeed.presentation.ui.policylist.view.DetailInfoActivity
import com.fork.spoonfeed.presentation.util.setBackBtnClickListener

class InterastedPolicyActivity : BaseViewUtil.BaseAppCompatActivity<ActivityInterastedPolicyBinding>(R.layout.activity_interasted_policy) {
    private lateinit var interastedPolicyListAdapter: PolicyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setInterastedPolicyListAdapter(
            mutableListOf(
                PolicyListResponseData(1, "주거", "청년 우대 통장", "아이조아아이조아아이조아", "2022.02-0222.02", 2),
                PolicyListResponseData(1, "주거", "청년 우대 통장", "아이조아아이조아아이조아", "2022.02-0222.02", 2),
                PolicyListResponseData(1, "금융", "청년 우대 통장", "아이조아아이조아아이조아", "2022.02-0222.02", 2),
            )
        )
        this.setBackBtnClickListener(binding.ivInterastedpolicyBack)
    }

    private fun setInterastedPolicyListAdapter(dataList: MutableList<PolicyListResponseData>) {
        interastedPolicyListAdapter = PolicyListAdapter(true, dataList) {
            Intent(this, DetailInfoActivity::class.java).apply {
                putExtra("category", it.category)
                putExtra("title", it.title)
                putExtra("id", it.id)
                putExtra("likeCount", it.likeCount)
                startActivity(this)
            }
        }
        with(binding) {
            rvInterastedpolicy.adapter = interastedPolicyListAdapter
            rvInterastedpolicy.layoutManager = LinearLayoutManager(this@InterastedPolicyActivity)
        }
    }
}