package com.fork.spoonfeed.presentation.ui.community.view

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentBottomDialogFilterBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.community.viewmodel.CommunityViewModel

class BottomDialogCommunityFragment() :
    BaseViewUtil.BaseCategoryBottomDialogFragment<FragmentBottomDialogFilterBinding>(R.layout.fragment_bottom_dialog_filter) {
    private val communityViewModel: CommunityViewModel by activityViewModels()
    private var isInitialized = false // bottom sheet initialize check

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        communityViewModel.selectedFilter.value?.let { setCategoryView(it) }
        applyFilterLayout()
        setClickListener()
        setCloseBtnClickedListener()
    }

    private fun applyFilterLayout() {
        communityViewModel.selectedFilter.observe(this) { selectedFilter ->
            if (isInitialized) {
                setCategoryView(selectedFilter)
                setHandler()
            } else {
                isInitialized = true
            }
        }
    }

    private fun setCategoryView(selectedFilter: String) {
        with(binding) {
            when (selectedFilter) {
                ALL -> {
                    setClickedCategory(tvBottomDialogAll)
                    setUnClickedCategory(tvBottomDialogDwelling, tvBottomDialogFinance)
                }
                DWELLING -> {
                    setClickedCategory(tvBottomDialogDwelling)
                    setUnClickedCategory(tvBottomDialogAll, tvBottomDialogFinance)
                }
                FINANCE -> {
                    setClickedCategory(tvBottomDialogFinance)
                    setUnClickedCategory(tvBottomDialogAll, tvBottomDialogDwelling)
                }
            }
        }
    }

    private fun setHandler() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ dialog?.dismiss() }, 140)
    }

    private fun setClickedCategory(category: TextView) {
        val typeFaceBold = Typeface.createFromAsset(requireActivity().assets, "suit_bold.otf")
        category.setTypeface(typeFaceBold)
    }

    private fun setUnClickedCategory(categoryOne: TextView, categoryTwo: TextView) {
        val typeFaceRegular =
            Typeface.createFromAsset(requireActivity().assets, "suit_regular.otf")
        categoryOne.setTypeface(typeFaceRegular)
        categoryTwo.setTypeface(typeFaceRegular)
    }

    private fun setClickListener() {
        binding.tvBottomDialogAll.setOnClickListener {
            communityViewModel.setCategorySelected(ALL)
        }
        binding.tvBottomDialogDwelling.setOnClickListener {
            communityViewModel.setCategorySelected(DWELLING)
        }
        binding.tvBottomDialogFinance.setOnClickListener {
            communityViewModel.setCategorySelected(FINANCE)
        }
    }

    private fun setCloseBtnClickedListener() {
        binding.ivBottomDialogClose.setOnClickListener {
            dialog?.dismiss()
        }
    }
}

