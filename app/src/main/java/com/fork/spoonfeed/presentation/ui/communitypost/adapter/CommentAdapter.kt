package com.fork.spoonfeed.presentation.ui.communitypost.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.databinding.ItemCommunityPostCommentBinding

data class CommentResponseData(
    val id: Int,
    val nickname: String,
    val deadline: String,
    val content: String
)

class CommentAdapter :
    ListAdapter<CommentResponseData, CommentAdapter.CommentViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            ItemCommunityPostCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class CommentViewHolder(private val binding: ItemCommunityPostCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(commentResponseData: CommentResponseData) {
            with(binding) {
                tvItemUserName.text = commentResponseData.nickname
                tvItemDeadline.text = commentResponseData.deadline
                tvItemExplain.text = commentResponseData.content
            }

            binding.executePendingBindings()
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CommentResponseData>() {
            override fun areContentsTheSame(
                oldItem: CommentResponseData,
                newItem: CommentResponseData
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: CommentResponseData,
                newItem: CommentResponseData
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }

    }
}
