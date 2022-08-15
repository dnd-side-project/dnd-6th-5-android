package com.fork.spoonfeed.presentation.ui.policylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import com.fork.spoonfeed.databinding.ItemPolicyListBinding
import com.fork.spoonfeed.presentation.ui.policylist.viewmodel.PolicyListViewModel
import com.fork.spoonfeed.presentation.util.SimpleDiffUtil

class PolicyListAdapter(
    private val context: LifecycleOwner,
    private val policyListViewModel: PolicyListViewModel,
    private val clickListener: (ResponsePolicyAllData.Data.Policy) -> Unit
) : ListAdapter<ResponsePolicyAllData.Data.Policy, PolicyListAdapter.PolicyListViewHolder>(SimpleDiffUtil()) {


    inner class PolicyListViewHolder(private val binding: ItemPolicyListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponsePolicyAllData.Data.Policy) {

            binding.apply {
                var likeCountInt = data.likeCount.toInt()
                tvItemCategory.text = data.category
                tvItemPolicyTitle.text = data.name
                tvItemPolicySentence.text = data.summary
                tvItemDeadline.text = data.applicationPeriod
                tvItemLikeCount.text = data.likeCount
                if (data.category == "금융") {
                    tvItemCategory.setBackgroundResource(R.drawable.bg_finance_purple_radius_4dp)
                }

                ctlItem.setOnClickListener {
                    clickListener(data)
                }

                ivItemLike.setOnClickListener {
                    ivItemLike.toggle()

                    if (ivItemLike.isChecked) {
                        tvItemLikeCount.text = (++likeCountInt).toString()
                    } else if (!ivItemLike.isChecked) {
                        tvItemLikeCount.text = (--likeCountInt).toString()
                        if (likeCountInt == -1) {
                            tvItemLikeCount.text = (++likeCountInt).toString()
                        }
                    }
                    policyListViewModel.postMyLikePolicy(data.id.toString())
                }

                policyListViewModel.myLikePolicyList.observe(context) { myLikePolicyList ->
                    for (list in myLikePolicyList) {
                        if (list.policyId == data.id)
                            ivItemLike.isChecked = true
                    }
                }

                policyListViewModel.likeBtnState.observe(context) { likeBtnState ->
                    if (likeBtnState.id == data.id && ivItemLike.isChecked != likeBtnState.likeState) {
                        ivItemLike.isChecked = likeBtnState.likeState
                        tvItemLikeCount.text = likeBtnState.ctn.toString()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PolicyListViewHolder {
        val binding = ItemPolicyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PolicyListViewHolder(binding)
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: PolicyListViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}
