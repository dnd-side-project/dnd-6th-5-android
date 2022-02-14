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
import android.util.Log
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
                        Log.d("필터선택", "1필터선탞ㄲㄲㄲㄲㄲㄲ")
                        dialog?.dismiss()
                    }
                    DWELLING -> {
                        setClickedCategory(tvBottomDialogDwelling)
                        setUnClickedCategory(tvBottomDialogAll, tvBottomDialogFinance)
                        Log.d("필터선택", "2필터선탞ㄲㄲㄲㄲㄲㄲ")
                    }
                    FINANCE -> {
                        setClickedCategory(tvBottomDialogFinance)
                        setUnClickedCategory(tvBottomDialogAll, tvBottomDialogDwelling)
                        Log.d("필터선택", "3필터선탞ㄲㄲㄲㄲㄲㄲ")
                    }
                }
            }
        }
    }

    private fun setClickedCategory(category: TextView) {
        val typeFaceBold = Typeface.createFromAsset(requireActivity().assets, "suit_bold.otf")
        category.setTypeface(typeFaceBold)
    }

    private fun setUnClickedCategory(categoryOne: TextView, categoryTwo: TextView) {
        val typeFaceRegular = Typeface.createFromAsset(requireActivity().assets, "suit_regular.otf")
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