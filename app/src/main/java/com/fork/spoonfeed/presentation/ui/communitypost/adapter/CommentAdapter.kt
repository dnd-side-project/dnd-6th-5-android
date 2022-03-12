package com.fork.spoonfeed.presentation.ui.communitypost.adapter

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData
import com.fork.spoonfeed.databinding.ItemCommunityPostCommentBinding
import com.fork.spoonfeed.presentation.util.dpToPx

class CommentAdapter(
    private val commentUpdateListener: (ResponsePostData.Data.Comment) -> (Unit),
    private val commentDeleteListener: (ResponsePostData.Data.Comment) -> (Unit)
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
        holder.bind(currentList[position], commentUpdateListener, commentDeleteListener)
    }

    class CommentViewHolder(private val binding: ItemCommunityPostCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            commentResponseData: ResponsePostData.Data.Comment,
            commentUpdateListener: (ResponsePostData.Data.Comment) -> Unit,
            commentDeleteListener: (ResponsePostData.Data.Comment) -> Unit
        ) {
            with(binding) {
                tvItemUserName.text = commentResponseData.commenter
                tvItemDeadline.text = commentResponseData.createdAt
                tvItemExplain.text = commentResponseData.content
                ivItemEdit.setOnClickListener {
                    showMenu(
                        binding.root.context,
                        binding,
                        commentResponseData,
                        commentUpdateListener,
                        commentDeleteListener
                    )
                }
            }

            binding.executePendingBindings()
        }

        private fun showMenu(
            context: Context,
            binding: ItemCommunityPostCommentBinding,
            data: ResponsePostData.Data.Comment,
            commentUpdateListener: (ResponsePostData.Data.Comment) -> (Unit),
            commentDeleteListener: (ResponsePostData.Data.Comment) -> (Unit)
        ) {
            val items = context.resources.getStringArray(R.array.mypage_popup)

            val popupAdapter =
                object : ArrayAdapter<String>(context, R.layout.item_mypage_popup, items) {
                    override fun getView(
                        position: Int,
                        convertView: View?,
                        parent: ViewGroup
                    ): View {
                        val view = super.getView(position, convertView, parent)
                        val color = if (position == 0) {
                            R.color.gray04
                        } else {
                            R.color.delete_red
                        }
                        (view as TextView).setTextColor(ContextCompat.getColor(context, color))
                        return view
                    }
                }

            val popup = ListPopupWindow(context).apply {
                anchorView = binding.ivItemEdit
                setAdapter(popupAdapter)
                setDropDownGravity(Gravity.NO_GRAVITY)
                width = context.dpToPx(169)
                height = context.dpToPx(112)
                setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.img_comment_bg))
            }

            popup.setOnItemClickListener { _, view, _, _ ->
                if ((view as TextView).text == "수정하기") {
                    commentUpdateListener(data)
                    popup.dismiss()
                } else {
                    commentDeleteListener(data)
                    popup.dismiss()
                }
            }
            popup.show()
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
