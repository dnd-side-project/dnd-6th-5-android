package com.fork.spoonfeed.presentation.ui.mypage.view

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityInterastedPolicyBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.policylist.adapter.PolicyListAdapter
import com.fork.spoonfeed.presentation.ui.policylist.view.DetailInfoActivity
import com.fork.spoonfeed.presentation.util.setBackBtnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InterestedPolicyActivity : BaseViewUtil.BaseAppCompatActivity<ActivityInterastedPolicyBinding>(R.layout.activity_interasted_policy) {
    private lateinit var interestedPolicyListAdapter: PolicyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setInterestedPolicyListAdapter()
        this.setBackBtnClickListener(binding.ivInterastedpolicyBack)
    }

    private fun setInterestedPolicyListAdapter() {
        interestedPolicyListAdapter = PolicyListAdapter(true) {
            Intent(this, DetailInfoActivity::class.java).apply {
                putExtra("category", it.category)
                putExtra("title", it.name)
                putExtra("id", it.id)
                putExtra("likeCount", it.likeCount)
                startActivity(this)
            }
        }
        with(binding) {
            rvInterastedpolicy.adapter = interestedPolicyListAdapter
            rvInterastedpolicy.layoutManager = LinearLayoutManager(this@InterestedPolicyActivity)
        }
    }
}
