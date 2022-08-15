package com.fork.spoonfeed.presentation.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.databinding.ItemCommentBinding
import com.fork.spoonfeed.databinding.ItemNoticeBinding
import com.fork.spoonfeed.presentation.util.SimpleDiffUtil


data class NoticeResponseData(
    val id: Int,
    val noticeTitle: String,
    val sentence: String,
    val writedDate: String,
)

class NoticeAdapter(
    private val postList: List<NoticeResponseData>,
    private val clickListener: (NoticeResponseData) -> Unit
) : ListAdapter<NoticeResponseData, NoticeAdapter.NoticeViewHolder>(SimpleDiffUtil()) {

    inner class NoticeViewHolder(private val binding: ItemNoticeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: NoticeResponseData) {
            binding.apply {
                tvItemNoticeTitle.text = data.noticeTitle
                tvItemNoticeWritedData.text = data.writedDate
                root.setOnClickListener {
                    clickListener(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(binding)
    }

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.onBind(postList[position])
    }
}