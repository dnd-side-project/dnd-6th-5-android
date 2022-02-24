package com.fork.spoonfeed.presentation.ui.communitypost.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData
import com.fork.spoonfeed.databinding.ItemCommunityPostCommentBinding

class CommentAdapter :
    ListAdapter<ResponsePostData.Data.Comment, CommentAdapter.CommentViewHolder>(diffUtil) {

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

        fun bind(commentResponseData: ResponsePostData.Data.Comment) {
            with(binding) {
                tvItemUserName.text = commentResponseData.commenter
                tvItemDeadline.text = commentResponseData.createdAt
                tvItemExplain.text = commentResponseData.content
            }

            binding.executePendingBindings()
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResponsePostData.Data.Comment>() {
            override fun areContentsTheSame(
                oldItem: ResponsePostData.Data.Comment,
                newItem: ResponsePostData.Data.Comment
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: ResponsePostData.Data.Comment,
                newItem: ResponsePostData.Data.Comment
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }

    }
}
