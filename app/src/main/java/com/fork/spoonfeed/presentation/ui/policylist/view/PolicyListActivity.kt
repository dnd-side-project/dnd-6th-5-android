package com.fork.spoonfeed.presentation.ui.policylist.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityPolicyListBinding
import com.fork.spoonfeed.presentation.MainActivity
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.policylist.adapter.PolicyListAdapter
import com.fork.spoonfeed.presentation.ui.policylist.viewmodel.PolicyListViewModel
import com.fork.spoonfeed.presentation.util.setBackBtnClickListener
import com.fork.spoonfeed.presentation.util.showFloatingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PolicyListActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityPolicyListBinding>(R.layout.activity_policy_list) {
    private val policyListViewModel: PolicyListViewModel by viewModels()
    private lateinit var policyListAdapter: PolicyListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.policyListViewModel = policyListViewModel
        binding.lifecycleOwner = this
        initView()
    }

    override fun initView() {
        setPolicyListAdapter()
        this.setBackBtnClickListener(binding.ivPolicylistBack)
        setPolicyListObserve()
        setFilterClickObserve()
        setReWriteClickObserve()
        setInitLayout()
        policyListViewModel.applyFilter()
    }

    private fun setInitLayout() {
        val category = intent.getStringExtra(CATEGORY)
        if (category != null) {
            with(binding) {
                tvPolicylistRewrite.visibility = View.INVISIBLE
                ivPolicylistEnterBack.visibility = View.INVISIBLE
                tvPolicylistTitle.text = "청년 정책"
            }
            policyListViewModel?.initSelectedFilter = category
            setFilterCategoryLayout(category)
        } else {
            showSearchDoneDialog()
        }
    }

    private fun setFilterCategoryLayout(category: String) {
        when (category) {
            ALL -> binding.tvPolicylistFilter.text = "전체"
            DWELLING -> binding.tvPolicylistFilter.text = "주거"
            FINANCE -> binding.tvPolicylistFilter.text = "금융"
        }
    }

    private fun setPolicyListAdapter() {
        policyListAdapter = PolicyListAdapter(false) {
            Intent(this, DetailInfoActivity::class.java).apply {
                putExtra("category", it.category)
                putExtra("title", it.name)
                putExtra("id", it.id)
                putExtra("likeCount", it.likeCount)
                startActivity(this)
            }
        }
        with(binding) {
            rvPolicylist.adapter = policyListAdapter
            rvPolicylist.layoutManager = LinearLayoutManager(this@PolicyListActivity)
        }
    }

    private fun setPolicyListObserve() {
        policyListViewModel.policyFilteredResult.observe(this) { policyList ->
            policyListAdapter.submitList(policyList)
        }
    }

    private fun setFilterClickObserve() {
        policyListViewModel.isFilterClicked.observe(this) { isFilterClicked ->
            isFilterClicked.let {
                if (isFilterClicked) {
                    showBottomFilterDialog()
                    setFilterClickLayoutObserve()
                }
            }
        }
    }

    private fun setFilterClickLayoutObserve() {
        policyListViewModel.selectedFilter.observe(this) { category ->
            setFilterCategoryLayout(category)
            policyListViewModel.applyFilter()
        }
    }

    private fun setReWriteClickObserve() {
        policyListViewModel.isReWriteClicked.observe(this) { isReWriteClicked ->
            isReWriteClicked.let {
                if (isReWriteClicked) {
                    showReWriteDialog()
                }
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun showSearchDoneDialog() {
        val dialog = this.showFloatingDialog(R.layout.dialog_policy_list_search_done)
        val confirmBtn = dialog.findViewById<Button>(R.id.tv_dialog_search_done_confirm)

        confirmBtn.setOnClickListener {
            dialog.dismiss()
        }
    }

    @SuppressLint("ResourceType")
    private fun showReWriteDialog() {
        val dialog = this.showFloatingDialog(R.layout.dialog_policy_list_rewrite)
        val confirmBtn = dialog.findViewById<Button>(R.id.tv_dialog_confirm)
        val cancelBtn = dialog.findViewById<Button>(R.id.tv_dialog_cancel)

        confirmBtn.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(baseContext, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                putExtra(POLICY_FILTER_RESET_NAME, POLICY_FILTER_RESET_VALUE)
            })
        }

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        policyListViewModel.reWriteOnClickFalse()
    }

    private fun showBottomFilterDialog() {
        val bottomSheetFragment = BottomDialogFilterFragment()
        bottomSheetFragment.show(
            supportFragmentManager,
            bottomSheetFragment.tag
        )
        policyListViewModel.filterOnClickFalse()
//        policyListViewModel.nothingSelected()
    }

    companion object {
        const val ALL = "ALL"
        const val DWELLING = "DWELLING"
        const val FINANCE = "FINANCE"
        const val CATEGORY = "CATEGORY"
        const val POLICY_FILTER_RESET_NAME = "com.fork.spoonfeed.presentation.ui.policylist"
        const val POLICY_FILTER_RESET_VALUE = "RESET"
    }
}
