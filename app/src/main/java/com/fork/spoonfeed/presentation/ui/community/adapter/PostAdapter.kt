package com.fork.spoonfeed.presentation.ui.community.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData
import com.fork.spoonfeed.databinding.ItemPostBinding
import com.fork.spoonfeed.presentation.ui.mypage.view.BottomDialogMyPageFragment

class PostAdapter(
    private val supportFragmentManager: FragmentManager,
    private val user: Boolean,
    private val clickListener: (ResponsePostAllData.Data.Post) -> Unit
) : ListAdapter<ResponsePostAllData.Data.Post, PostAdapter.CommunityViewHolder>(diffUtil) {

    inner class CommunityViewHolder(
        private val binding: ItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: ResponsePostAllData.Data.Post) {
            binding.apply {
                tvItemCategory.text = data.category
                tvItemPolicyTitle.text = data.title
                tvItemUserName.text = data.author
                tvItemCreated.text = data.createdAt
                tvItemPolicyExplain.text = data.content
                tvItemCommentCount.text = data.commentCount

                if (data.category == "금융") {
                    tvItemCategory.setBackgroundResource(R.drawable.bg_finance_purple_radius_4dp)
                }

                ivItemPostEdit.setOnClickListener {
                    showBottomDialog(data.id)
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

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResponsePostAllData.Data.Post>() {
            override fun areContentsTheSame(
                oldItem: ResponsePostAllData.Data.Post,
                newItem: ResponsePostAllData.Data.Post
            ) =
                oldItem == newItem

            override fun areItemsTheSame(
                oldItem: ResponsePostAllData.Data.Post,
                newItem: ResponsePostAllData.Data.Post
            ) =
                oldItem.id == newItem.id
        }
    }

    private fun showBottomDialog(postId: Int) {
        val bottomSheetFragment = BottomDialogMyPageFragment(postId, BottomDialogMyPageFragment.MANAGEMENT_NO_COMMENT, BottomDialogMyPageFragment.MANAGEMENT_POST)
        bottomSheetFragment.show(
            supportFragmentManager,
            bottomSheetFragment.tag
        )
    }
}
