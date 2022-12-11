package com.sertac.vodafonechallange.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.sertac.vodafonechallange.R
import com.sertac.vodafonechallange.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
            .addOnDestinationChangedListener { controller, destination, arguments ->
                binding.backButtonTextView.visibility = View.VISIBLE
                binding.pageTitleTextView.text = when (destination.id) {
                    R.id.chooserFragment -> {
                        "Choose an action".also {
                            binding.backButtonTextView.visibility = View.GONE
                        }
                    }
                    R.id.userProfileFragment -> "User Detail"
                    R.id.repositoriesFragment -> "Repositories"
                    R.id.repositoryDetailFragment -> "Repository Detail"
                    else -> "MISSING HEADER!!"
                }
            }
        onClickBackButtonTextView()
    }

    private fun onClickBackButtonTextView() {
        binding.backButtonTextView.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}