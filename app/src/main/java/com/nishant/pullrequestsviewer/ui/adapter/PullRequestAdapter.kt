package com.nishant.pullrequestsviewer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nishant.pullrequestsviewer.databinding.PullRequestItemBinding
import com.nishant.pullrequestsviewer.model.PullRequest

class PullRequestAdapter :
    ListAdapter<PullRequest, PullRequestAdapter.PullRequestViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val binding = PullRequestItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PullRequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class PullRequestViewHolder(private val binding: PullRequestItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: PullRequest) {
            binding.model = model
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<PullRequest>() {
        override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest) =
            oldItem == newItem
    }
}
