package com.mt.vodafonecasestudy.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.mt.vodafonecasestudy.R
import com.mt.vodafonecasestudy.databinding.FragmentRepoDetailBinding

import com.mt.vodafonecasestudy.model.ReposItem
import com.mt.vodafonecasestudy.model.RepositoriesItem
import com.mt.vodafonecasestudy.viewmodel.RepoViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class RepositoryDetailFragment : Fragment(R.layout.fragment_repo_detail) {

    private var _binding: FragmentRepoDetailBinding? = null
    private val binding get() = _binding!!
    private val args: RepositoryDetailFragmentArgs by navArgs()
    private lateinit var repo: RepositoriesItem
    private val viewModel: RepoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoDetailBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repo = args.repos
        setRV()

    }

    private fun setRV() {
        binding.apply {
            binding.imageView.load(repo.owner.avatar_url) {
                crossfade(true)
                crossfade(2000)
            }
            viewModel.reposResponse.observe(requireActivity()) { result ->
                val newRes: ReposItem? =result.find { item-> item.name.equals(repo.name) }
                binding.repoNameText.text = newRes?.name
                binding.ownerUsernameText.text = newRes?.full_name
                binding.forkCount.text = newRes?.forks_count.toString()
                binding.languageText.text = newRes?.language
                binding.dfBrancNameText.text = newRes?.default_branch
            }


        }
        binding.imageView.setOnClickListener{ mView->
            val direction = RepositoryDetailFragmentDirections.actionRepositoryDetailFragment3ToUserFragment3()
            mView.findNavController().navigate(direction)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}