package com.fork.spoonfeed.presentation.ui.mypage.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityInterastedPolicyBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.mypage.adapter.MyLikePolicyAdapter
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.fork.spoonfeed.presentation.ui.policylist.view.DetailInfoActivity
import com.fork.spoonfeed.presentation.util.setBackBtnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InterestedPolicyActivity : BaseViewUtil.BaseAppCompatActivity<ActivityInterastedPolicyBinding>(R.layout.activity_interasted_policy) {
    private lateinit var myLikePolicyAdapter: MyLikePolicyAdapter
    private val myPageViewModel: MyPageViewModel by viewModels()

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
        myPageViewModel.isMyInterastedPolicyEmpty.observe(this) { isMyInterastedPolicyEmpty ->
            isMyInterastedPolicyEmpty.let {
                if (isMyInterastedPolicyEmpty) {
                    binding.rvLikepolicy.visibility = View.INVISIBLE
                    binding.ctlLikepolicyNoComment.visibility = View.VISIBLE
                } else {
                    binding.ctlLikepolicyNoComment.visibility = View.INVISIBLE
                    binding.rvLikepolicy.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setMyLikePostListObserve() {
        myPageViewModel.myLikePoLicyList.observe(this) { myLikePoLicyList ->
            myLikePolicyAdapter.submitList(myLikePoLicyList)
        }
    }


    private fun initRvAdapter() {
        myLikePolicyAdapter = MyLikePolicyAdapter {
            Intent(this, DetailInfoActivity::class.java).apply {
                /*  putExtra("category", it.category)
                  putExtra("title", it.name)
                  putExtra("id", it.id)
                  putExtra("likeCount", it.likeCount)
                  startActivity(this)*/
            }
        }
        with(binding) {
            rvLikepolicy.adapter = myLikePolicyAdapter
            rvLikepolicy.layoutManager = LinearLayoutManager(this@InterestedPolicyActivity)
        }
    }
}
