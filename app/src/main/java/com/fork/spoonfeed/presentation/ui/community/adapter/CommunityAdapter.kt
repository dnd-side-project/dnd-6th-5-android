package com.fork.spoonfeed.presentation.ui.community.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ItemCommunityBinding

data class CommunityResponseData(
    val id: Int,
    val category: String,
    val title: String,
    val nickname: String,
    val sentence: String,
    val deadline: String,
    val commentCount: Int
)

class CommunityAdapter(
    private val postList: List<CommunityResponseData>,
    private val clickListener: (CommunityResponseData) -> Unit
) : ListAdapter<CommunityResponseData, CommunityAdapter.CommunityViewHolder>(diffUtil) {

    inner class CommunityViewHolder(private val binding: ItemCommunityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: CommunityResponseData) {
            binding.apply {
                tvItemCategory.text = data.category
                tvItemPolicyTitle.text = data.title
                tvItemUserName.text = data.nickname
                tvItemDeadline.text = data.deadline
                tvItemPolicyExplain.text = data.sentence
                tvItemCommentCount.text = data.commentCount.toString()
                if (data.category == "주거") {
                    tvItemCategory.setBackgroundResource(R.drawable.bg_dwelling_blue_radius_4dp)
                }
                ctlItem.setOnClickListener {
                    clickListener(data)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val binding = ItemCommunityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityViewHolder(binding)
    }

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        holder.onBind(postList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CommunityResponseData>() {
            override fun areContentsTheSame(oldItem: CommunityResponseData, newItem: CommunityResponseData) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: CommunityResponseData, newItem: CommunityResponseData) =
                oldItem.id == newItem.id
        }
    }
}
