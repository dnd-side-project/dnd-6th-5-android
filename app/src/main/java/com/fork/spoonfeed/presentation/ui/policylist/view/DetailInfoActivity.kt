package com.fork.spoonfeed.presentation.ui.policylist.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityDetailInfoBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.policylist.viewmodel.DetailInfoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailInfoActivity : BaseViewUtil.BaseAppCompatActivity<ActivityDetailInfoBinding>(R.layout.activity_detail_info) {
    private val detailInfoViewModel: DetailInfoViewModel by viewModels()
    private val youthCenterUrl = "https://www.youthcenter.go.kr/main.do"
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
                    putExtra(LIMITED_AGE, detailInfoViewModel!!.policyDetailInfo.value?.limitAge)
                    putExtra(LIMITED_AREA_ASSET, detailInfoViewModel!!.policyDetailInfo.value?.limitAreaAsset)
                    putExtra(SPECIALIZATION, detailInfoViewModel!!.policyDetailInfo.value?.specialization)
                    startActivity(this)
                }
            }
            ivDetailInfoApplyExplain.setOnClickListener {
                Intent(this@DetailInfoActivity, ApplyExplainActivity::class.java).apply {
                    putExtra(CONTENT, detailInfoViewModel!!.policyDetailInfo.value?.content)
                    putExtra(OTHER_INFO, detailInfoViewModel!!.policyDetailInfo.value?.otherInfo)
                    putExtra(LIMITED_TARGET, detailInfoViewModel!!.policyDetailInfo.value?.limitedTarget)
                    putExtra(SUPPORT_SCALE, detailInfoViewModel!!.policyDetailInfo.value?.supportScale)
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
                for (element in list)
                    if (element.policyId == id) {
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
            startWithLike()
            finish()
        }
    }

    override fun onBackPressed() {
        startWithLike()
        super.onBackPressed()
    }

    private fun startWithLike() {
        val intent = Intent(this@DetailInfoActivity, PolicyListActivity::class.java)
            .putExtra(ID, id)
            .putExtra(LIKE_STATE, binding.ivDetailInfoLike.isChecked)
            .putExtra(CTN, binding.tvDetailInfoLikeNum.text.toString().toInt())
        setResult(RESULT_OK, intent)
    }

    companion object {
        const val CONTENT = "CONTENT"
        const val OTHER_INFO = "OTHER_INFO"
        const val LIMITED_TARGET = "LIMITED_TARGET"
        const val SUPPORT_SCALE = "SUPPORT_SCALE"
        const val LIMITED_AGE = "LIMITED_AGE"
        const val LIMITED_AREA_ASSET = "LIMITED_AREA_ASSET"
        const val SPECIALIZATION = "SPECIALIZATION"
        const val ID = "ID"
        const val LIKE_STATE = "LIKE_STATE"
        const val CTN = "CTN"
        const val POST_PK = "com.fork.spoonfeed.presentation.ui.mypage.view"
        const val REFERENCE_SITE_NOTHING = "-"

        fun start(context: Context, pk: Int) {
            val intent = Intent(context, DetailInfoActivity::class.java).putExtra(POST_PK, pk)
            context.startActivity(intent)
        }
    }
}
