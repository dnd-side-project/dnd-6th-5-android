package com.fork.spoonfeed.presentation.ui.communitypost.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserData
import com.fork.spoonfeed.databinding.ItemCommunityPostCommentBinding
import com.fork.spoonfeed.domain.model.CommentData
import com.fork.spoonfeed.presentation.ui.mypage.view.BottomDialogMyPageFragment

class CommentAdapter(
    private val supportFragmentManager: FragmentManager,
    private val userData: ResponseUserData.Data.User,
    private val commentUpdateListener: (ResponsePostData.Data.Comment) -> Unit,
    private val commentDeleteListener: (ResponsePostData.Data.Comment) -> Unit
) : ListAdapter<ResponsePostData.Data.Comment, CommentAdapter.CommentViewHolder>(diffUtil) {

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
        holder.bind(currentList[position], userData, commentUpdateListener, commentDeleteListener)
    }

    inner class CommentViewHolder(private val binding: ItemCommunityPostCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            commentResponseData: ResponsePostData.Data.Comment,
            userData: ResponseUserData.Data.User,
            commentUpdateListener: (ResponsePostData.Data.Comment) -> Unit,
            commentDeleteListener: (ResponsePostData.Data.Comment) -> Unit
        ) {
            with(binding) {
                tvItemUserName.text = commentResponseData.commenter
                tvItemDeadline.text = commentResponseData.createdAt
                tvItemExplain.text = commentResponseData.content
                ivItemEdit.isVisible = commentResponseData.commenter == userData.nickname
                if (ivItemEdit.isVisible) {
                    ivItemEdit.setOnClickListener {
                        showBottomDialog(
                          commentResponseData.id.toString(), commentResponseData,
                            commentUpdateListener,
                            commentDeleteListener
                        )
                    }
                }
            }

            binding.executePendingBindings()
        }

        private fun showBottomDialog(
        commentPk: String, data: ResponsePostData.Data.Comment,
            commentUpdateListener: (ResponsePostData.Data.Comment) -> (Unit),
            commentDeleteListener: (ResponsePostData.Data.Comment) -> (Unit)
        ) {

            val bottomSheetFragment = BottomDialogMyPageFragment()
            bottomSheetFragment.arguments = Bundle().apply {
                putString(BottomDialogMyPageFragment.COMMENT_PK, commentPk)
                putString(BottomDialogMyPageFragment.EDIT_TYPE, BottomDialogMyPageFragment.COMMUNITY_COMMENT)
                putSerializable(BottomDialogMyPageFragment.COMMENT_DATA, CommentData(data, commentUpdateListener, commentDeleteListener))
            }
            bottomSheetFragment.show(
                supportFragmentManager,
                bottomSheetFragment.tag
            )
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

