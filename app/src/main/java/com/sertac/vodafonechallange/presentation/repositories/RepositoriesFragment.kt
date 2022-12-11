package com.sertac.vodafonechallange.presentation.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sertac.vodafonechallange.databinding.FragmentRepositoriesBinding
import com.sertac.vodafonechallange.presentation.repositories.adapter.OnRepoClickListener
import com.sertac.vodafonechallange.presentation.repositories.adapter.RepositoriesAdapter
import com.sertac.vodafonechallange.presentation.userprofile.UserProfileFragmentDirections

class RepositoriesFragment : Fragment() {

    private var _binding: FragmentRepositoriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var reposAdapter: RepositoriesAdapter
    private lateinit var viewModel: RepositoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[RepositoriesViewModel::class.java]

        createAdapterAndSet()
        subscribeObservers()
        getRepositories()
    }

    private fun subscribeObservers() {
        viewModel.showLoadingReposLiveData.observe(viewLifecycleOwner){
            binding.repositoriesLoadingProgressBar.visibility = it
        }
        viewModel.repositoriesLiveData.observe(viewLifecycleOwner){
            reposAdapter.repoList = it
        }
    }

    private fun getRepositories() {
        viewModel.getRepos()
    }

    private fun createAdapterAndSet() {
        reposAdapter = RepositoriesAdapter(requireContext(), object : OnRepoClickListener {
            override fun onRepoClicked(index: Int?) {
                index?.let {
                    val userRepo = reposAdapter.repoList?.get(it)
                    val action =
                        RepositoriesFragmentDirections.actionRepositoriesFragmentToRepositoryDetailFragment(
                            userRepo?.repositoryOwner?.avatarUrl,
                            userRepo?.description,
                            userRepo?.repositoryOwner?.login,
                            userRepo?.name,
                            userRepo?.private == true
                        )
                    findNavController().navigate(action)
                }
            }

        })
        binding.reposRecyclerView.adapter = reposAdapter
    }
}