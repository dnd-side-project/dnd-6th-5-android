package com.fork.spoonfeed.presentation.ui.policylist

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentBottomDialogFilterBinding
import com.fork.spoonfeed.databinding.FragmentPolicyBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BottomDialogFilterFragment(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomDialogFilterBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomDialogFilterBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), R.style.NewDialog)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeCard()
    }

    private fun makeCard() {
        binding.apply {
            tvBottomDialogAll.setOnClickListener {
                tvBottomDialogAll.setTextColor(requireContext().getColor(R.color.primary_blue))
                itemClick(ALL)
                dialog?.dismiss()
            }
            tvBottomDialogDwelling.setOnClickListener {
                tvBottomDialogDwelling.setTextColor(requireContext().getColor(R.color.primary_blue))
                itemClick(DWELLING)
                dialog?.dismiss()
            }
            tvBottomDialogFinance.setOnClickListener {
                tvBottomDialogFinance.setTextColor(requireContext().getColor(R.color.primary_blue))
                tvBottomDialogFinance.setFont(requireContext().getColor(R.color.primary_blue))
                itemClick(FINANCE)
                dialog?.dismiss()
            }
            ivBottomDialogClose.setOnClickListener {
                dialog?.dismiss()
            }
        }
    }

    companion object {
        const val ALL = 0
        const val DWELLING = 1
        const val FINANCE = 2
    }
}