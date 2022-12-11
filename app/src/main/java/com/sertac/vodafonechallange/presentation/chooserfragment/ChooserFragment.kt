package com.sertac.vodafonechallange.presentation.chooserfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sertac.vodafonechallange.databinding.FragmentChooserBinding


class ChooserFragment : Fragment() {

    private var _binding: FragmentChooserBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickEvents()

    }

    private fun onClickEvents() {

        binding.repositoriesLayout.setOnClickListener {
            val action = ChooserFragmentDirections.actionChooserFragmentToRepositoriesFragment()
            findNavController().navigate(action)
        }
        binding.userDetailLayout.setOnClickListener {
            val action = ChooserFragmentDirections.actionChooserFragmentToUserProfileFragment(null)
            findNavController().navigate(action)
        }
    }

    override fun getView(): View? {
        return super.getView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}