package com.sertac.vodafonechallange.presentation.repositories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sertac.core.domain.repository.Repository
import com.sertac.vodafonechallange.databinding.RepositoryItemBinding

class RepositoriesAdapter(
    private val context: Context,
    private val repoClickListener: OnRepoClickListener
) :
    RecyclerView.Adapter<RepositoriesAdapter.RepositoryViewHolder>() {

    var repoList: List<Repository?>? = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class RepositoryViewHolder(var binding: RepositoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repository) {
            binding.ownerNameTextView.text = repo.repositoryOwner?.login ?: "-No Owner-"
            binding.repoNameTextView.text = repo.name

            Glide.with(context).load(repo.repositoryOwner?.avatarUrl)
                .into(binding.userAvatarImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding =
            RepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            repoClickListener.onRepoClicked(position)
        }

        repoList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        repoList?.let {
            return it.size
        }
        return 0
    }
}