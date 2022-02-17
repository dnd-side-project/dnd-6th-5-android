package com.fork.spoonfeed.presentation.ui.community.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ItemPostBinding

data class PostResponseData(
    val id: Int,
    val category: String,
    val title: String,
    val nickname: String,
    val sentence: String,
    val deadline: String,
    val commentCount: Int
)

class PostAdapter(
    private val user: Boolean,
    private val postList: List<PostResponseData>,
    private val clickListener: (PostResponseData) -> Unit
) : ListAdapter<PostResponseData, PostAdapter.CommunityViewHolder>(diffUtil) {

    inner class CommunityViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PostResponseData) {
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
                if (user) {
                    ivItemPostEdit.visibility = View.VISIBLE
                }
                ctlItem.setOnClickListener {
                    clickListener(data)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityViewHolder(binding)
    }

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        holder.onBind(postList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PostResponseData>() {
            override fun areContentsTheSame(oldItem: PostResponseData, newItem: PostResponseData) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: PostResponseData, newItem: PostResponseData) =
                oldItem.id == newItem.id
        }
    }
}
