package com.fork.spoonfeed.presentation.ui.policylist.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityDetailInfoBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.policylist.viewmodel.DetailInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.fork.spoonfeed.presentation.util.setBackBtnClickListener


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
        setDetailInfo()
        setCategoryObserve()
        this.setBackBtnClickListener(binding.ivDetailInfoBack)
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

    private fun setDetailInfo() {
        val id = intent.getIntExtra(POST_PK, 2)
        detailInfoViewModel.getPolicyDetailInfo(id)
    }

    private fun setCategoryObserve() {
        detailInfoViewModel.policyDetailInfo.observe(this) {
            if (it.category == "금융") {
                binding.tvDetailInfoCategory.setBackgroundResource(R.drawable.bg_finance_purple_radius_4dp)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ///좋아요 버튼 서버통신
    }

    companion object {
        const val POST_PK = "com.fork.spoonfeed.presentation.ui.mypage.view"
    }
}