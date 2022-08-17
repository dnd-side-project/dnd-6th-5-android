package com.fork.spoonfeed.presentation.ui.mypage.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityInterastedPolicyBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.community.view.CommunityFragment
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostActivity
import com.fork.spoonfeed.presentation.ui.home.viewmodel.HomeViewModel
import com.fork.spoonfeed.presentation.ui.mypage.adapter.MyLikePolicyAdapter
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.fork.spoonfeed.presentation.ui.policylist.view.DetailInfoActivity
import com.fork.spoonfeed.presentation.ui.policylist.view.PolicyListActivity
import com.fork.spoonfeed.presentation.util.setBackBtnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InterestedPolicyActivity : BaseViewUtil.BaseAppCompatActivity<ActivityInterastedPolicyBinding>(R.layout.activity_interasted_policy) {
    private lateinit var myLikePolicyAdapter: MyLikePolicyAdapter
    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        initData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.myPageViewModel = myPageViewModel
        initView()
    }

    override fun initView() {
        initData()
        initRvAdapter()
        setMyLikePostEmptyObserve()
        setMyLikePostListObserve()
        this.setBackBtnClickListener(binding.ivLikepolicyBack)
    }

    private fun initData() {
        myPageViewModel.getMyLikePolicy()
    }

    private fun setMyLikePostEmptyObserve() {
        myPageViewModel.isMyLikePolicyListEmpty.observe(this) { isMyLikePolicyListEmpty ->
            if (isMyLikePolicyListEmpty) {
                binding.rvLikepolicy.visibility = View.GONE
                binding.ctlLikepolicyNoComment.visibility = View.VISIBLE
            } else {
                binding.ctlLikepolicyNoComment.visibility = View.GONE
                binding.rvLikepolicy.visibility = View.VISIBLE
            }
        }
    }

    private fun setMyLikePostListObserve() {
        myPageViewModel.myLikePolicyList.observe(this) { myLikePolicyList ->
            myLikePolicyAdapter.submitList(myLikePolicyList)
        }
        myPageViewModel.postMyLikePolicySuccess.observe(this) { postMyLikePolicySuccess ->
            if (postMyLikePolicySuccess)
                initData()
        }
    }


    private fun initRvAdapter() {
        myLikePolicyAdapter = MyLikePolicyAdapter(myPageViewModel) {
            Intent(this, DetailInfoActivity::class.java).apply {
                putExtra(DetailInfoActivity.POST_PK, it.policyId)
                startActivity(this)
            }
        }
        with(binding) {
            rvLikepolicy.adapter = myLikePolicyAdapter
            rvLikepolicy.layoutManager = LinearLayoutManager(this@InterestedPolicyActivity)
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MyLikePolicyAdapter::class.java)
            context.startActivity(intent)
        }
    }
}
