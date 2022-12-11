package com.sertac.vodafonechallange.presentation.userprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.sertac.vodafonechallange.databinding.FragmentUserProfileBinding
import com.sertac.vodafonechallange.presentation.userprofile.adapter.OnRepoClickListener
import com.sertac.vodafonechallange.presentation.userprofile.adapter.UserReposAdapter


class UserProfileFragment : Fragment() {

    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserProfileViewModel
    private lateinit var userReposAdapter: UserReposAdapter
    private lateinit var userName: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userName = "traex"
        arguments?.let { bundle ->
            UserProfileFragmentArgs.fromBundle(bundle).userName?.let { name ->
                userName = name
            }
        }
        viewModel = ViewModelProvider(requireActivity())[UserProfileViewModel::class.java]
        createAdapterAndSet()
        callUserProfileServiceOnStart()
        subscribeObservers()
    }

    private fun createAdapterAndSet() {
        userReposAdapter = UserReposAdapter(object : OnRepoClickListener {
            override fun onRepoClicked(index: Int?) {
                index?.let {
                    val userRepo = userReposAdapter.repoList?.get(it)
                    val action =
                        UserProfileFragmentDirections.actionUserProfileFragmentToRepositoryDetailFragment(
                            userRepo?.repositoryOwner?.avatarUrl,
                            userRepo?.description,
                            userRepo?.repositoryOwner?.login,
                            userRepo?.name,
                            userRepo?.private == true
                        )
                    val extras = FragmentNavigatorExtras(binding.userAvatarImageView to "userImage")
                    findNavController().navigate(action,extras)
                }
            }

        })
        binding.userReposRecyclerView.adapter = userReposAdapter
    }

    private fun callUserProfileServiceOnStart() {
        viewModel.getUserProfileData(userName)
        viewModel.getUserReposWithName(userName)
    }

    private fun subscribeObservers() {
        viewModel.userProfileLiveData.observe(viewLifecycleOwner) {
            it?.let {
                context?.let { nonNullContext ->
                    Glide.with(nonNullContext).load(it.avatarUrl).into(binding.userAvatarImageView)
                }
                binding.userNameTextView.text = it.name
                binding.userInformationTextView.apply {
                    text = ""
                    append("Company: ${it.company}\n")
                    append("Location: ${it.location}\n")
                    append("Public Repos: ${it.publicRepos}\tPublic Gists: ${it.publicGists}\n")
                    append("Followers: ${it.followers}\tFollowings: ${it.following}\n")
                    append("Created at: ${it.createdAt}\n")
                    append("Updated at: ${it.updatedAt}\n")
                }
            }
        }
        viewModel.showLoadingUserProfileLiveData.observe(viewLifecycleOwner) {
            binding.userProfileProgressBar.visibility = it
        }
        viewModel.showLoadingUserRepoLiveData.observe(viewLifecycleOwner) {
            binding.userReposProgressBar.visibility = it
        }

        viewModel.userRepositoriesLiveData.observe(viewLifecycleOwner) { userRepos ->
            userRepos?.let {
                userReposAdapter.repoList = it
            }
        }
    }
}