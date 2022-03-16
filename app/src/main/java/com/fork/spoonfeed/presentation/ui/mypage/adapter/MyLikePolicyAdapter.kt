package com.fork.spoonfeed.presentation.ui.mypage.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.remote.model.user.ResponseUserLikePolicyData
import com.fork.spoonfeed.databinding.ItemPolicyListBinding
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel

class MyLikePolicyAdapter(
    private val myPageViewModel: MyPageViewModel,
    private val clickListener: (ResponseUserLikePolicyData.Data.Policy) -> Unit
) : ListAdapter<ResponseUserLikePolicyData.Data.Policy, MyLikePolicyAdapter.MyLikePolicyViewHolder>(diffUtil) {

    inner class MyLikePolicyViewHolder(private val binding: ItemPolicyListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseUserLikePolicyData.Data.Policy) {
            binding.apply {
                var likeCountInt = data.cnt.toInt()
                tvItemPolicyTitle.text = data.name
                tvItemPolicySentence.text = data.content
                tvItemCategory.text = data.category
                tvItemDeadline.text = data.applicationPeriod
                tvItemLikeCount.text = data.cnt

                if (data.category == "금융") {
                    tvItemCategory.setBackgroundResource(R.drawable.bg_finance_purple_radius_4dp)
                }else{
                    tvItemCategory.setBackgroundResource(R.drawable.bg_dwelling_blue_radius_4dp)
                }

                root.setOnClickListener {
                    clickListener(data)
                }

                ivItemLike.isChecked = true
                ivItemLike.setOnClickListener {
                    ivItemLike.toggle()

                    if (ivItemLike.isChecked) {
                        tvItemLikeCount.text = (++likeCountInt).toString()
                    } else if (!ivItemLike.isChecked) {
                        tvItemLikeCount.text = (--likeCountInt).toString()
                    }
                    myPageViewModel.postMyLikePolicy(data.policyId.toString())
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