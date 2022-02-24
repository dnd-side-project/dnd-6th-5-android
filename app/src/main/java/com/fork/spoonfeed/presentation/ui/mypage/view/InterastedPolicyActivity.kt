package com.fork.spoonfeed.presentation.ui.mypage.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityInterastedPolicyBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.mypage.adapter.MyInterastedPolicyAdapter
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.fork.spoonfeed.presentation.ui.policylist.adapter.PolicyListAdapter
import com.fork.spoonfeed.presentation.ui.policylist.view.DetailInfoActivity
import com.fork.spoonfeed.presentation.util.setBackBtnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InterestedPolicyActivity : BaseViewUtil.BaseAppCompatActivity<ActivityInterastedPolicyBinding>(R.layout.activity_interasted_policy) {
    private lateinit var myInterastedPolicyAdapter: MyInterastedPolicyAdapter
    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.myPageViewModel = myPageViewModel
        initView()
    }

    override fun initView() {
        myPageViewModel.getMyInterastedPolicy()
        setisInterastedEmptyObserve()
        setInterestedPolicyListAdapter()
        setInterastedListObserve()
        this.setBackBtnClickListener(binding.ivInterastedpolicyBack)
    }

    private fun setisInterastedEmptyObserve() {
        myPageViewModel.isMyInterastedPolicyEmpty.observe(this) { isMyInterastedPolicyEmpty ->
            isMyInterastedPolicyEmpty.let {
                if (isMyInterastedPolicyEmpty) {
                    binding.rvInterastedpolicy.visibility = View.INVISIBLE
                    binding.ctlInterastedpolicyNoComment.visibility = View.VISIBLE
                } else {
                    binding.ctlInterastedpolicyNoComment.visibility = View.INVISIBLE
                    binding.rvInterastedpolicy.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setInterastedListObserve() {
        myPageViewModel.myInterastedtPoLicyList.observe(this) { myInterastedtPoLicyList ->
            myInterastedPolicyAdapter.submitList(myInterastedtPoLicyList)
        }
    }


    private fun setInterestedPolicyListAdapter() {
        myInterastedPolicyAdapter = MyInterastedPolicyAdapter {
            Intent(this, DetailInfoActivity::class.java).apply {
                /*  putExtra("category", it.category)
                  putExtra("title", it.name)
                  putExtra("id", it.id)
                  putExtra("likeCount", it.likeCount)
                  startActivity(this)*/
            }
        }
        with(binding) {
            rvInterastedpolicy.adapter = myInterastedPolicyAdapter
            rvInterastedpolicy.layoutManager = LinearLayoutManager(this@InterestedPolicyActivity)
        }
    }
}
