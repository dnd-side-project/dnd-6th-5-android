package com.fork.spoonfeed.presentation.ui.policylist.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.remote.model.policy.RequestFilteredPolicy
import com.fork.spoonfeed.databinding.ActivityPolicyListBinding
import com.fork.spoonfeed.domain.model.LikeBtnState
import com.fork.spoonfeed.presentation.MainActivity
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.base.BaseViewUtil.BaseCategoryBottomDialogFragment.Companion.ALL
import com.fork.spoonfeed.presentation.base.BaseViewUtil.BaseCategoryBottomDialogFragment.Companion.DWELLING
import com.fork.spoonfeed.presentation.base.BaseViewUtil.BaseCategoryBottomDialogFragment.Companion.FINANCE
import com.fork.spoonfeed.presentation.ui.policy.view.filter.PolicyFilterLevelThreeFragment
import com.fork.spoonfeed.presentation.ui.policylist.adapter.PolicyListAdapter
import com.fork.spoonfeed.presentation.ui.policylist.adapter.PolicyMenuAdapter
import com.fork.spoonfeed.presentation.ui.policylist.viewmodel.PolicyListViewModel
import com.fork.spoonfeed.presentation.util.setBackBtnClickListener
import com.fork.spoonfeed.presentation.util.showFloatingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PolicyListActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityPolicyListBinding>(R.layout.activity_policy_list) {
    private val policyListViewModel: PolicyListViewModel by viewModels()
    private lateinit var policyListAdapter: PolicyListAdapter
    private lateinit var policyMenuAdapter: PolicyMenuAdapter
    private lateinit var concatAdapter: ConcatAdapter
    private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LifeCycleEventLogger(javaClass.name).registerLogger(lifecycle)
        binding.policyListViewModel = policyListViewModel
        binding.lifecycleOwner = this
        initView()
    }

    override fun onResume() {
        super.onResume()
        policyListViewModel.getMyLikePolicy()
    }

    override fun initView() {
        setBottomNav()
        setPolicyListAdapter()
        setPolicyListObserve()
        setFilterClickObserve()
        setReWriteClickObserve()
        setLikeBtnResult()
        policyListViewModel.applyFilter()
        policyListViewModel.getMyLikePolicy()
        this.setBackBtnClickListener(binding.ivPolicylistBack)
    }

    private fun setBottomNav() {
        binding.bnvPolicylist.selectedItemId = R.id.menu_main_policy
        binding.bnvPolicylist.setOnItemSelectedListener {
            return@setOnItemSelectedListener when (it.itemId) {
                R.id.menu_main_home -> {
                    moveToMenu(MainActivity.BOTTOM_HOME)
                    true
                }
                R.id.menu_main_policy -> {
                    moveToMenu(MainActivity.BOTTOM_POLICY)
                    true
                }
                R.id.menu_main_community -> {
                    moveToMenu(MainActivity.BOTTOM_COMMUNITY)
                    true
                }
                R.id.menu_main_mypage -> {
                    moveToMenu(MainActivity.BOTTOM_MYPAGE)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun moveToMenu(targetFragment: String) {
        startActivity(Intent(baseContext, MainActivity::class.java).apply {
            putExtra(MainActivity.BOTTOM_MOVE, targetFragment)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }

    private fun setInitLayout() {
        val userInfo = intent.getSerializableExtra(PolicyFilterLevelThreeFragment.USER_FILTER_INFO)
                as RequestFilteredPolicy?
        if (userInfo != null) {
            policyListViewModel.setUserInfo(userInfo)
        }

        val category = intent.getStringExtra(CATEGORY)
        if (category != null) {
            with(binding) {
                tvPolicylistTitle.text = "청년 정책"
            }
            policyListViewModel.setCategorySelected(category)
        } else {
            showSearchDoneDialog()
        }
    }

/*    private fun setFilterCategoryLayout(category: String) {
        when (category) {
            ALL ->  policyMenuAdapter.clickFilter(ALL)     //binding.tvPolicylistFilter.text = ALL
            DWELLING ->  policyMenuAdapter.clickFilter(DWELLING)   //binding.tvPolicylistFilter.text = DWELLING
            FINANCE ->  policyMenuAdapter.clickFilter(FINANCE)   //binding.tvPolicylistFilter.text = FINANCE
        }
    }*/

    private fun setPolicyListAdapter() {
        policyListAdapter = PolicyListAdapter(this, policyListViewModel) {
            Intent(this, DetailInfoActivity::class.java).apply {
                putExtra(DetailInfoActivity.POST_PK, it.id)
                getResultText.launch(this)
            }
        }
        policyMenuAdapter = PolicyMenuAdapter({ policyListViewModel.reWriteOnClick() }, { policyListViewModel.filterOnClick() })
        with(binding) {
            concatAdapter = ConcatAdapter(policyMenuAdapter, policyListAdapter)
            rvPolicylist.adapter = concatAdapter
            rvPolicylist.layoutManager = LinearLayoutManager(this@PolicyListActivity)
        }

        setInitLayout()
    }

    private fun setLikeBtnResult() {
        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val id = result.data?.getIntExtra("id", -1)
                val ctn = result.data?.getIntExtra("ctn", -1)
                val likeState = result.data?.getBooleanExtra("likeState", false)
                policyListViewModel.setLikeBtn(LikeBtnState(id!!, ctn!!, likeState!!))
            }
        }
    }

    private fun setPolicyListObserve() {
        policyListViewModel.policyFilteredResult.observe(this) { policyList ->
            policyListViewModel.getMyLikePolicy()
            binding.clPolicylistEmptyData.visibility =
                if (policyList.isNullOrEmpty()) View.VISIBLE else View.INVISIBLE
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
            // setFilterCategoryLayout(category)
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
                putExtra(MainActivity.BOTTOM_MOVE, MainActivity.BOTTOM_POLICY)
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
    }

    companion object {
        const val CATEGORY = "CATEGORY"
        const val POLICY_FILTER_RESET_NAME = "com.fork.spoonfeed.presentation.ui.policylist"
        const val POLICY_FILTER_RESET_VALUE = "RESET"
    }
}
