package com.fork.spoonfeed.presentation.ui.communitypost.view

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ListPopupWindow
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityCommunityPostCreateBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fork.spoonfeed.data.remote.model.user.ResponseUserData
import com.fork.spoonfeed.presentation.ui.communitypost.viewmodel.CommunityPostCreateViewModel
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
        setEditField()
        setObserver()
        setOnClickListener()
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
//                TODO 게시물 ID 값을 받아서 작성한 글의 상세페이지로 이동하도록 구현
//                startActivity(Intent(baseContext, CommunityPostActivity::class.java))
            }
        })
    }

    private fun setOnClickListener() {
        binding.mbCommunityPostCreate.setOnClickListener {
            communityPostCreateViewModel.sendPost()
        }
        binding.mtCommunityPostCreateTitle.setNavigationOnClickListener {
            communityPostCreateViewModel.sendPost()
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
}
