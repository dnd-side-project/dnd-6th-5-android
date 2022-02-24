package com.fork.spoonfeed.presentation.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.data.remote.model.user.ResponseUserUserLikePolicyData
import com.fork.spoonfeed.databinding.ItemPolicyListBinding

class MyInterastedPolicyAdapter(
    private val clickListener: (ResponseUserUserLikePolicyData.Data.Policy) -> Unit
) : ListAdapter<ResponseUserUserLikePolicyData.Data.Policy, MyInterastedPolicyAdapter.MyInterastedViewHolder>(diffUtil) {

    inner class MyInterastedViewHolder(private val binding: ItemPolicyListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseUserUserLikePolicyData.Data.Policy) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyInterastedPolicyAdapter.MyInterastedViewHolder {
        val binding = ItemPolicyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyInterastedViewHolder(binding)
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: MyInterastedPolicyAdapter.MyInterastedViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResponseUserUserLikePolicyData.Data.Policy>() {
            override fun areContentsTheSame(oldItem: ResponseUserUserLikePolicyData.Data.Policy, newItem: ResponseUserUserLikePolicyData.Data.Policy) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: ResponseUserUserLikePolicyData.Data.Policy, newItem: ResponseUserUserLikePolicyData.Data.Policy) =
                oldItem.policyId == newItem.policyId
        }
    }
}