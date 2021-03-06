package com.fork.spoonfeed.presentation.ui.policylist.view

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
import com.fork.spoonfeed.presentation.ui.policylist.viewmodel.PolicyListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BottomDialogFilterFragment() :
    BaseViewUtil.BaseCategoryBottomDialogFragment<FragmentBottomDialogFilterBinding>(R.layout.fragment_bottom_dialog_filter) {

    private val policyListViewModel: PolicyListViewModel by activityViewModels()
    private var isInitialized = false // bottom sheet initialize check

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        policyListViewModel.selectedFilter.value?.let { setCategoryView(it) }
        applyFilterLayout()
        setCloseBtnClickedListener()
        setClickListener()
    }

    private fun applyFilterLayout() {
        policyListViewModel.selectedFilter.observe(this) { selectedFilter ->
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
            policyListViewModel.setCategorySelected(ALL)
        }
        binding.tvBottomDialogDwelling.setOnClickListener {
            policyListViewModel.setCategorySelected(DWELLING)
        }
        binding.tvBottomDialogFinance.setOnClickListener {
            policyListViewModel.setCategorySelected(FINANCE)
        }
    }

    private fun setCloseBtnClickedListener() {
        binding.ivBottomDialogClose.setOnClickListener {
            dialog?.dismiss()
        }
    }
}
