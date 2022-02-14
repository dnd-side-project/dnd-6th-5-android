package com.fork.spoonfeed.presentation.ui.policylist.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentBottomDialogFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import android.graphics.Typeface
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.fork.spoonfeed.presentation.ui.policylist.viewmodel.PolicyListViewModel


@AndroidEntryPoint
class BottomDialogFilterFragment() : BottomSheetDialogFragment() {
    private val policyListViewModel: PolicyListViewModel by activityViewModels()
    private var _binding: FragmentBottomDialogFilterBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomDialogFilterBinding.inflate(layoutInflater, container, false)
        binding.policyListViewModel = policyListViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), R.style.NewDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBottomDialogClose.setOnClickListener {
            policyListViewModel.applyFilter()
            dialog?.dismiss()
        }
        applyFilterLayout()
    }


    private fun applyFilterLayout() {

        policyListViewModel.selectedFileter.observe(this) { selectedFileter ->
            with(binding) {
                when (selectedFileter) {
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
    }

    private fun setClickedCategory(category: TextView) {
        val typeFaceBold = Typeface.createFromAsset(requireActivity().assets, "suit_bold.otf")
        category.setTextColor(requireContext().getColor(R.color.primary_blue))
        category.setTypeface(typeFaceBold)
    }

    private fun setUnClickedCategory(categoryOne: TextView, categoryTwo: TextView) {
        val typeFaceRegular = Typeface.createFromAsset(requireActivity().assets, "suit_regular.otf")
        categoryOne.setTextColor(requireContext().getColor(R.color.gray06))
        categoryTwo.setTextColor(requireContext().getColor(R.color.gray06))
        categoryOne.setTypeface(typeFaceRegular)
        categoryTwo.setTypeface(typeFaceRegular)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NOTHING = 0
        const val ALL = 1
        const val DWELLING = 2
        const val FINANCE = 3
    }
}