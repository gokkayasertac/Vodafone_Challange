package com.sertac.vodafonechallange.presentation.repositorydetail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

            binding.repoNameTextView.text = args.repoName
            binding.userNameTextView.text = args.ownerName
            if (args.private) {
                binding.privateTextView.text = "Private"
                binding.privateTextView.setTextColor(Color.RED)
            } else {
                binding.privateTextView.text = "Public"
                binding.privateTextView.setTextColor(Color.GREEN)
            }
            binding.repoDescriptionTextView.text = args.description

        }
        binding.userNameTextView.setOnClickListener {
            val action = RepositoryDetailFragmentDirections.actionRepositoryDetailFragmentToUserProfileFragment(
                binding.userNameTextView.text.toString()
            )
            findNavController().navigate(action)
        }
    }

}