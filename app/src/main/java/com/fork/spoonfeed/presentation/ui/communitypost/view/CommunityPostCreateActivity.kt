package com.fork.spoonfeed.presentation.ui.communitypost.view

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.remote.model.user.ResponseUserData
import com.fork.spoonfeed.databinding.ActivityCommunityPostCreateBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.communitypost.viewmodel.CommunityPostCreateViewModel
import com.fork.spoonfeed.presentation.ui.communitypost.viewmodel.CommunityPostViewModel
import com.fork.spoonfeed.presentation.util.dpToPx
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityPostCreateActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityCommunityPostCreateBinding>(R.layout.activity_community_post_create) {

    private val communityPostCreateViewModel: CommunityPostCreateViewModel by viewModels()
    private lateinit var registerForActivity: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRegisterForActivityResult()
        initView()
    }

    private fun setRegisterForActivityResult() {
        registerForActivity =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                communityPostCreateViewModel.setUserInfo(
                    it.data?.getSerializableExtra(CommunityPostInfoUpdateActivity.INFO_UPDATE_RESULT)
                            as ResponseUserData.Data.User
                )

                communityPostCreateViewModel.isValid()
                setUpdateButtonActive()
            }
    }

    override fun initView() {
        setEditMyPost()
        setObserver()
        setEditField()
    }

    private fun setEditMyPost() {
        val postId = intent.getIntExtra(POST_ID, RESULT_OK)
        if (postId != RESULT_OK) {
            binding.mbCommunityPostCreate.text = "수정하기"
            communityPostCreateViewModel.initData(postId)
            setEditObserve()
            setOnClickListener(true, postId)
        } else {
            setOnClickListener(false, postId)

        }
    }

    private fun setEditObserve() {
        with(binding) {
            communityPostCreateViewModel.postDetailData.observe(this@CommunityPostCreateActivity, {
                tvCommunityPostCreateCategory.text = it.category
                etCommunityPostCreateTitle.setText(it.title)
                etCommunityPostCreateContent.setText(it.content)
                communityPostCreateViewModel.setCategory(it.category)
                communityPostCreateViewModel.setTitle(it.title)
                communityPostCreateViewModel.setContent(it.content)
                if (it.category == "금융") {
                    tvCommunityPostCreateCategory.setTextColor(getColor(R.color.finance_purple))
                } else {
                    tvCommunityPostCreateCategory.setTextColor(getColor(R.color.dwelling_blue))
                }
                val typeFaceBold = Typeface.createFromAsset(assets, "suit_bold.otf")
                binding.tvCommunityPostCreateCategory.setTypeface(typeFaceBold)

            })
        }
    }

    private fun setEditField() {
        binding.etCommunityPostCreateTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    communityPostCreateViewModel.setTitle(null)
                } else {
                    communityPostCreateViewModel.setTitle(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        binding.etCommunityPostCreateContent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    communityPostCreateViewModel.setContent(null)
                } else {
                    communityPostCreateViewModel.setContent(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setObserver() {
        communityPostCreateViewModel.category.observe(this, {
            communityPostCreateViewModel.isValid()
        })
        communityPostCreateViewModel.title.observe(this, {
            communityPostCreateViewModel.isValid()
        })
        communityPostCreateViewModel.content.observe(this, {
            communityPostCreateViewModel.isValid()
        })
        communityPostCreateViewModel.isValid.observe(this, {
            setNextButtonActive(it)
        })
        communityPostCreateViewModel.sendSuccess.observe(this, {
            if (it) {
                finish()
            }
        })
        communityPostCreateViewModel.patchSuccess.observe(this, {
            if (it) {
                finish()
            }
        })
    }


    private fun setOnClickListener(edit: Boolean, postId: Int) {
        binding.mtCommunityPostCreateTitle.setNavigationOnClickListener {
            finish()
        }
        if (edit) {
            binding.mbCommunityPostCreate.setOnClickListener {
                communityPostCreateViewModel.editPost(postId)
            }
        } else {
            binding.mbCommunityPostCreate.setOnClickListener {
                communityPostCreateViewModel.sendPost()
            }
        }

        binding.tvCommunityPostCreateCategory.setOnClickListener {
            showMenu()
        }
        binding.mbCommunityPostCreateUpdateDetail.setOnClickListener {
            registerForActivity.launch(
                Intent(baseContext, CommunityPostInfoUpdateActivity::class.java)
            )
        }
    }

    private fun setUpdateButtonActive() {
        with(binding.mbCommunityPostCreateUpdateDetail) {
            icon = ContextCompat.getDrawable(context, R.drawable.ic_check_round_filled)
            iconTint = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.primary_blue))
            setTextColor(ContextCompat.getColor(context, R.color.primary_blue))
            setBackgroundColor(ContextCompat.getColor(context, R.color.secondary_lightblue))
            strokeColor =
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.primary_blue))
        }
    }

    private fun setNextButtonActive(isActive: Boolean) {
        binding.mbCommunityPostCreate.isEnabled = isActive
        binding.mbCommunityPostCreate.backgroundTintList = if (isActive) {
            ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.primary_blue))
        } else {
            ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.gray03))
        }
    }

    private fun showMenu() {
        val items = resources.getStringArray(R.array.category_popup)

        // TODO 텍스트 스타일 적용이 안되는 문제 해결 필요
        val popupAdapter =
            object : ArrayAdapter<String>(baseContext, R.layout.item_category_popup, items) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getView(position, convertView, parent)
                    val color = if (position == 0) {
                        R.color.dwelling_blue
                    } else {
                        R.color.finance_purple
                    }
                    (view as TextView).setTextColor(ContextCompat.getColor(context, color))
                    return view
                }
            }

        val popup = ListPopupWindow(baseContext).apply {
            anchorView = binding.tvCommunityPostCreateCategory
            setAdapter(popupAdapter)
            setDropDownGravity(Gravity.NO_GRAVITY)
            width = dpToPx(102)
            height = ListPopupWindow.WRAP_CONTENT
            setBackgroundDrawable(ContextCompat.getDrawable(baseContext, R.drawable.bg_category))
        }

        popup.setOnItemClickListener { _, view, _, _ ->
            val textColor = if ((view as TextView).text == "금융") {
                R.color.finance_purple
            } else {
                R.color.dwelling_blue
            }

            with(binding.tvCommunityPostCreateCategory) {
                text = view.text
                setTextAppearance(R.style.TextView_Heading_Bold_14)
                setTextColor(ContextCompat.getColor(context, textColor))
                communityPostCreateViewModel.setCategory(view.text.toString())
            }
        }
        popup.show()
    }

    companion object {
        const val POST_ID = "POSTID"
    }
}
