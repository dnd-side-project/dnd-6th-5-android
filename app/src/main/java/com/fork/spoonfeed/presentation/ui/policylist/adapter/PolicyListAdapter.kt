package com.fork.spoonfeed.presentation.ui.policylist.adapter

import android.app.Activity
import android.util.Log
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

class PolicyListAdapter(
    private val context: LifecycleOwner,
    private val policyListViewModel: PolicyListViewModel,
    private val clickListener: (ResponsePolicyAllData.Data.Policy) -> Unit
) : ListAdapter<ResponsePolicyAllData.Data.Policy, PolicyListAdapter.PolicyListViewHolder>(diffUtil) {


    inner class PolicyListViewHolder(private val binding: ItemPolicyListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponsePolicyAllData.Data.Policy) {
            binding.apply {
                var  likeCountInt = data.likeCount.toInt()
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
                    }
                    policyListViewModel.postMyLikePolicy(data.id.toString())
                }

                policyListViewModel.myLikePolicyList.observe(context) { myLikePolicyList ->
                    for (list in myLikePolicyList) {
                        if (list.policyId == data.id)
                            ivItemLike.isChecked = true

                        if(likeCountInt==0)
                            ivItemLike.isChecked = false
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

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResponsePolicyAllData.Data.Policy>() {
            override fun areContentsTheSame(oldItem: ResponsePolicyAllData.Data.Policy, newItem: ResponsePolicyAllData.Data.Policy) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: ResponsePolicyAllData.Data.Policy, newItem: ResponsePolicyAllData.Data.Policy) =
                oldItem.id == newItem.id
        }
    }
}
