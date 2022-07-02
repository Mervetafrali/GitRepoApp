package com.mt.vodafonecasestudy.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.mt.vodafonecasestudy.R
import com.mt.vodafonecasestudy.adapter.ReposAdapter
import com.mt.vodafonecasestudy.databinding.FragmentRepoDetailBinding
import com.mt.vodafonecasestudy.databinding.FragmentRepositoriesBinding
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
    private lateinit var reposAdapter: ReposAdapter
    private val args: RepositoryDetailFragmentArgs by navArgs()
    private lateinit var repo:RepositoriesItem
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
        repo=args.repo
        setRV()

    }
    private fun setRV(){
        reposAdapter=ReposAdapter()
        binding.repos.apply {
            layoutManager= GridLayoutManager(activity,1)
            setHasFixedSize(true)
            adapter=reposAdapter
        }
        viewModel.reposResponse.observe(requireActivity()){result->
            reposAdapter.repoDetail=result
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}