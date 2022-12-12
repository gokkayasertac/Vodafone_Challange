package com.sertac.vodafonechallange.presentation.repositorydetail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.sertac.vodafonechallange.R
import com.sertac.vodafonechallange.databinding.FragmentRepositoryDetailBinding


class RepositoryDetailFragment : Fragment() {

    private var _binding: FragmentRepositoryDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoryDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val args = RepositoryDetailFragmentArgs.fromBundle(it)
            context?.let { nonNullContext ->
                Glide.with(nonNullContext)
                    .load(args.repoAvatar)
                    .into(binding.repoOwnerAvatarImageView)
            }
            setValueOrHideView(binding.repoNameTextView, args.repoName)
            setValueOrHideView(binding.userNameTextView, args.ownerName)
            setValueOrHideView(binding.avatarEmailTextView, args.avatarEmail)
            setValueOrHideView(binding.forkCountTextView, args.forkCount)
            setValueOrHideView(binding.languageTextView, args.language)
            setValueOrHideView(binding.defaultBranchNameTextView, args.defBranchName)

        }
        binding.repoOwnerAvatarImageView.setOnClickListener {
            val action = RepositoryDetailFragmentDirections.actionRepositoryDetailFragmentToUserProfileFragment(
                binding.userNameTextView.text.toString()
            )
            findNavController().navigate(action)
        }

    }

    private fun setValueOrHideView(view: TextView, value: String?) {
        if(value.isNullOrEmpty())
            view.visibility = View.GONE
        else
            view.text = value
    }

}