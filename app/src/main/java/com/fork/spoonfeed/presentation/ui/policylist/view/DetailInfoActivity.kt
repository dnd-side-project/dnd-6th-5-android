package com.fork.spoonfeed.presentation.ui.policylist.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityDetailInfoBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.policy.view.PolicyFragment
import com.fork.spoonfeed.presentation.ui.policy.view.filter.PolicyFilterActivity
import com.fork.spoonfeed.presentation.ui.policylist.viewmodel.DetailInfoViewModel
import com.fork.spoonfeed.presentation.util.replace
import dagger.hilt.android.AndroidEntryPoint
import com.fork.spoonfeed.presentation.MainActivity


@AndroidEntryPoint
class DetailInfoActivity : BaseViewUtil.BaseAppCompatActivity<ActivityDetailInfoBinding>(R.layout.activity_detail_info) {
    private val detailInfoViewModel: DetailInfoViewModel by viewModels()
    private val youthCenterUrl = "https://www.youthcenter.go.kr/main.do"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.detailInfoViewModel = detailInfoViewModel
        binding.lifecycleOwner = this
        initView()
    }

    override fun initView() {
        initClickListener()
        setLikeBtn()
        setBackBtnClickListener()
    }

    private fun setBackBtnClickListener() {
        binding.ivDetailInfoBack.setOnClickListener {
            finish()
        }
    }

    private fun initClickListener() {
        with(binding) {
            ivDetailInfoLike.setOnClickListener {
                ivDetailInfoLike.toggle()
                ivDetailInfoLikeBottom.toggle()
            }

            ivDetailInfoLikeBottom.setOnClickListener {
                ivDetailInfoLikeBottom.toggle()
                ivDetailInfoLike.toggle()
            }

            ivDetailInfoApplyQualifications.setOnClickListener {
                val intent = Intent(this@DetailInfoActivity, ApplyQualificationActivity::class.java)
                startActivity(intent)
            }

            ivDetailInfoApplyExplain.setOnClickListener {
                val intent = Intent(this@DetailInfoActivity, ApplyExplainActivity::class.java)
                startActivity(intent)
            }
            btnDetailInfoGotoCustomPolicy.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youthCenterUrl))
                startActivity(intent)
            }
        }
    }

    private fun setLikeBtn() {
        if (detailInfoViewModel.isLikeClicked.value ?: false) {
            with(binding) {
                ivDetailInfoLike.isChecked = true
                ivDetailInfoLikeBottom.isChecked = true
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ///좋아요 버튼 서버통신
    }
}