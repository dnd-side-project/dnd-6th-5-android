package com.fork.spoonfeed.presentation.ui.policylist.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityDetailInfoBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.policylist.viewmodel.DetailInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailInfoActivity : BaseViewUtil.BaseAppCompatActivity<ActivityDetailInfoBinding>(R.layout.activity_detail_info) {
    private val detailInfoViewModel: DetailInfoViewModel by viewModels()
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