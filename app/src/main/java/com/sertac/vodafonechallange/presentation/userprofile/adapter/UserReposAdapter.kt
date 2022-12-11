package com.sertac.vodafonechallange.presentation.userprofile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sertac.core.domain.userrepository.UserRepository
import com.sertac.vodafonechallange.databinding.UserRepoItemBinding

class UserReposAdapter(private val repoClickListener: OnRepoClickListener) :
    RecyclerView.Adapter<UserReposAdapter.RepoViewHolder>() {

    var repoList: List<UserRepository?>? = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class RepoViewHolder(var binding: UserRepoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: UserRepository) {
            binding.ownerNameTextView.text = repo.repositoryOwner?.login ?: "-No Owner-"
            binding.repoNameTextView.text = repo.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding =
            UserRepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
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