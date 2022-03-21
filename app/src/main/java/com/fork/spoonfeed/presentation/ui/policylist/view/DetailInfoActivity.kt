package com.fork.spoonfeed.presentation.ui.policylist.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.remote.model.user.ResponseUserLikePolicyData
import com.fork.spoonfeed.databinding.ActivityDetailInfoBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.policylist.viewmodel.DetailInfoViewModel
import com.fork.spoonfeed.presentation.ui.policylist.viewmodel.PolicyListViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.fork.spoonfeed.presentation.util.setBackBtnClickListener


@AndroidEntryPoint
class DetailInfoActivity : BaseViewUtil.BaseAppCompatActivity<ActivityDetailInfoBinding>(R.layout.activity_detail_info) {
    private val detailInfoViewModel: DetailInfoViewModel by viewModels()
    private val youthCenterUrl = "https://www.youthcenter.go.kr/main.do"
    private lateinit var referenceSite1: String
    private var id = 0

    private var initLikeState = false
    private var likeState = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.detailInfoViewModel = detailInfoViewModel
        binding.lifecycleOwner = this
        initView()
    }

    override fun initView() {

        initClickListener()
        setInitLikeBtn()
        setDetailInfo()
        setCategoryObserve()
        setBackBtn()
        setReferenceSiteInitLayout()
    }

    private fun setReferenceSiteInitLayout() {
        with(binding) {
            detailInfoViewModel!!.referenceSiteOne.observe(this@DetailInfoActivity) { referenceSiteOne ->
                if (referenceSiteOne == REFERENCE_SITE_NOTHING) {
                    viewReferenceOne.visibility = View.INVISIBLE
                } else {
                    viewReferenceOne.visibility = View.VISIBLE
                    referenceOneClickListener(referenceSiteOne)
                }
            }
            detailInfoViewModel!!.referenceSiteTwo.observe(this@DetailInfoActivity) { referenceSiteTwo ->
                if (referenceSiteTwo == REFERENCE_SITE_NOTHING) {
                    viewReferenceTwo.visibility = View.INVISIBLE
                } else {
                    viewReferenceTwo.visibility = View.VISIBLE
                    referenceTwoClickListener(referenceSiteTwo)
                }
            }
        }
    }

    private fun initClickListener() {
        with(binding) {

            ivDetailInfoLike.setOnClickListener {
                switchLikeBtn()
            }

            ivDetailInfoLikeBottom.setOnClickListener {
                switchLikeBtn()
            }

            ivDetailInfoApplyQualifications.setOnClickListener {
                Intent(this@DetailInfoActivity, ApplyQualificationActivity::class.java).apply {
                    putExtra("limitAge", detailInfoViewModel!!.policyDetailInfo.value!!.limitAge)
                    putExtra("limitAreaAsset", detailInfoViewModel!!.policyDetailInfo.value!!.limitAreaAsset)
                    putExtra("specialization", detailInfoViewModel!!.policyDetailInfo.value!!.specialization)
                    startActivity(this)
                }
            }

            ivDetailInfoApplyExplain.setOnClickListener {
                Intent(this@DetailInfoActivity, ApplyExplainActivity::class.java).apply {
                    putExtra("content", detailInfoViewModel!!.policyDetailInfo.value!!.content)
                    putExtra("otherInfo", detailInfoViewModel!!.policyDetailInfo.value!!.otherInfo)
                    putExtra("limitedTarget", detailInfoViewModel!!.policyDetailInfo.value!!.limitedTarget)
                    putExtra("supportScale", detailInfoViewModel!!.policyDetailInfo.value!!.supportScale)
                    startActivity(this)
                }
            }
            btnDetailInfoGotoCustomPolicy.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youthCenterUrl))
                startActivity(intent)
            }
        }
    }

    private fun referenceOneClickListener(referenceOne: String) {
        binding.tvDetailInfoReferenceSiteOneSentence.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(referenceOne))
            startActivity(intent)
        }
    }

    private fun referenceTwoClickListener(referenceTwo: String) {
        binding.tvDetailInfoReferenceSiteTwoSentence.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(referenceTwo))
            startActivity(intent)
        }
    }

    private fun setInitLikeBtn() {
        detailInfoViewModel.getMyLikePolicy()

        detailInfoViewModel.myLikePolicyList.observe(this) {
            val list = it
            if (list != null) {
                for (item in 0 until list.size)
                    if (list[item].policyId == id) {
                        with(binding) {
                            initLikeState = true
                            likeState = true
                            ivDetailInfoLike.isChecked = true
                            ivDetailInfoLikeBottom.isChecked = true
                        }
                    }
            }
        }
    }

    private fun switchLikeBtn() {
        with(binding) {

            ivDetailInfoLike.toggle()
            ivDetailInfoLikeBottom.toggle()
            likeState = !likeState

            var likeCountInt = tvDetailInfoLikeNum.text.toString().toInt()
            if (ivDetailInfoLike.isChecked) {
                tvDetailInfoLikeNum.text = (++likeCountInt).toString()
            } else if (!ivDetailInfoLike.isChecked) {
                tvDetailInfoLikeNum.text = (--likeCountInt).toString()
            }
        }
        detailInfoViewModel.postMyLikePolicy(id.toString())
    }

    private fun setDetailInfo() {
        id = intent.getIntExtra(POST_PK, 0)
        detailInfoViewModel.getPolicyDetailInfo(id)
    }

    private fun setCategoryObserve() {
        detailInfoViewModel.policyDetailInfo.observe(this) {
            if (it.category == "금융") {
                binding.tvDetailInfoCategory.setBackgroundResource(R.drawable.bg_finance_purple_radius_4dp)
            }
        }
    }

    private fun setBackBtn() {
        binding.ivDetailInfoBack.setOnClickListener {
            val intent = Intent(this@DetailInfoActivity, PolicyListActivity::class.java)
                .putExtra("id", id)
                .putExtra("likeState", binding.ivDetailInfoLike.isChecked)
                .putExtra("ctn", binding.tvDetailInfoLikeNum.text.toString().toInt())
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    companion object {
        const val POST_PK = "com.fork.spoonfeed.presentation.ui.mypage.view"
        const val REFERENCE_SITE_NOTHING = "-"
    }
}
