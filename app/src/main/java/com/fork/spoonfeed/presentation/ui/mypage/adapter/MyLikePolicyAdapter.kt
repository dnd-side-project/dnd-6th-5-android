package com.fork.spoonfeed.presentation.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.data.remote.model.user.ResponseUserLikePolicyData
import com.fork.spoonfeed.databinding.ItemPolicyListBinding

class MyLikePolicyAdapter(
    private val clickListener: (ResponseUserLikePolicyData.Data.Policy) -> Unit
) : ListAdapter<ResponseUserLikePolicyData.Data.Policy, MyLikePolicyAdapter.MyLikePolicyViewHolder>(diffUtil) {

    inner class MyLikePolicyViewHolder(private val binding: ItemPolicyListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseUserLikePolicyData.Data.Policy) {
            binding.apply {
                tvItemPolicySentence.text = data.content
                tvItemCategory.text = data.category
                tvItemDeadline.text = data.applicationPeriod
                tvItemLikeCount.text = data.cnt
                root.setOnClickListener {
                    clickListener(data)
                }
                ivItemLike.isChecked = true
                ivItemLike.setOnClickListener {
                    ivItemLike.toggle()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyLikePolicyAdapter.MyLikePolicyViewHolder {
        val binding = ItemPolicyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyLikePolicyViewHolder(binding)
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: MyLikePolicyAdapter.MyLikePolicyViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResponseUserLikePolicyData.Data.Policy>() {
            override fun areContentsTheSame(oldItem: ResponseUserLikePolicyData.Data.Policy, newItem: ResponseUserLikePolicyData.Data.Policy) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: ResponseUserLikePolicyData.Data.Policy, newItem: ResponseUserLikePolicyData.Data.Policy) =
                oldItem.policyId == newItem.policyId
        }
    }
}