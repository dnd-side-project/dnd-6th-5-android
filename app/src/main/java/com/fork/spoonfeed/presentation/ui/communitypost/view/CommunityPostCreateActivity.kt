package com.fork.spoonfeed.presentation.ui.communitypost.view

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ListPopupWindow
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityCommunityPostCreateBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.fork.spoonfeed.presentation.util.dpToPx

class CommunityPostCreateActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityCommunityPostCreateBinding>(R.layout.activity_community_post_create) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.mbCommunityPostCreate.setOnClickListener {
            startActivity(Intent(baseContext, CommunityPostActivity::class.java))
            finish()
        }
        binding.mtCommunityPostCreateTitle.setNavigationOnClickListener {
            finish()
        }
        binding.tvCommunityPostCreateCategory.setOnClickListener {
            showMenu()
        }
        binding.mbCommunityPostCreateUpdateDetail.setOnClickListener {
            startActivity(Intent(baseContext, CommunityPostInfoUpdateActivity::class.java))

            // TODO 현재 뷰모델 없이 임의로 상세정보 수정 버튼을 누르면 활성화 되도록 구현
            // 뷰모델과 연결지어 모든 값이 입력되고, 수정페이지 방문 후 현재 화면으로 돌아왔을때 활성화되도록 수정 필요
            setButtonActive()
        }
    }

    private fun setButtonActive() {
        with(binding.mbCommunityPostCreateUpdateDetail) {
            icon = ContextCompat.getDrawable(context, R.drawable.ic_check_round_filled)
            iconTint = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.primary_blue))
            setTextColor(ContextCompat.getColor(context, R.color.primary_blue))
            setBackgroundColor(ContextCompat.getColor(context, R.color.secondary_lightblue))
            strokeColor =
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.primary_blue))
        }

        binding.mbCommunityPostCreate.isEnabled = true
        binding.mbCommunityPostCreate.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.primary_blue))
    }


    private fun showMenu() {
        val items = resources.getStringArray(R.array.category_popup)

        // TODO 텍스트 스타일 적용이 안되는 문제 해결 필요
        val popupAdapter =
            object : ArrayAdapter<String>(baseContext, R.layout.item_category_popup, items) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getView(position, convertView, parent)
                    val color = if (position == 0) {
                        R.color.finance_purple
                    } else {
                        R.color.dwelling_blue
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
            }
        }
        popup.show()
    }
}
