package com.fork.spoonfeed.presentation.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.remote.model.user.ResponseUserPostData
import com.fork.spoonfeed.databinding.ItemPostBinding
import com.fork.spoonfeed.presentation.ui.mypage.view.BottomDialogMyPageFragment
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel

class MyPostAdapter(
    private val supportFragmentManager: FragmentManager,
    private val myPageViewModel: MyPageViewModel,
    private val clickListener: (ResponseUserPostData.Data.Post) -> Unit
) : ListAdapter<ResponseUserPostData.Data.Post, MyPostAdapter.MyPostViewHolder>(diffUtil) {

    inner class MyPostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseUserPostData.Data.Post) {
            binding.apply {
                ivItemPostEdit.visibility = View.VISIBLE
                tvItemPolicyExplain.text = data.content
                tvItemCategory.text = data.category
                tvItemCreated.text = data.createdAt
                tvItemPolicyTitle.text = data.title
                tvItemCommentCount.text = data.cnt
                tvItemUserName.text = myPageViewModel.userNickName.value

                if (data.category == "금융") {
                    tvItemCategory.setBackgroundResource(R.drawable.bg_finance_purple_radius_4dp)
                } else {
                    tvItemCategory.setBackgroundResource(R.drawable.bg_dwelling_blue_radius_4dp)
                }

                root.setOnClickListener {
                    clickListener(data)
                }
                ivItemPostEdit.setOnClickListener {
                    showBottomDialog(data.postId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostAdapter.MyPostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPostViewHolder(binding)
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: MyPostAdapter.MyPostViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    private fun showBottomDialog(postId: Int) {
        val bottomSheetFragment = BottomDialogMyPageFragment(postId, BottomDialogMyPageFragment.MANAGEMENT_NO_COMMENT, BottomDialogMyPageFragment.MANAGEMENT_POST)
        bottomSheetFragment.show(
            supportFragmentManager,
            bottomSheetFragment.tag
        )
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResponseUserPostData.Data.Post>() {
            override fun areContentsTheSame(oldItem: ResponseUserPostData.Data.Post, newItem: ResponseUserPostData.Data.Post) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: ResponseUserPostData.Data.Post, newItem: ResponseUserPostData.Data.Post) =
                oldItem.postId == newItem.postId
        }
    }
}
