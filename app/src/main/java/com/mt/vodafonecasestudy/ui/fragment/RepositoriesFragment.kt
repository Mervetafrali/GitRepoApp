package com.mt.vodafonecasestudy.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mt.vodafonecasestudy.R
import com.mt.vodafonecasestudy.adapter.RepositoriesAdapter
import com.mt.vodafonecasestudy.databinding.FragmentRepositoriesBinding
import com.mt.vodafonecasestudy.viewmodel.RepoViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class RepositoriesFragment : Fragment(R.layout.fragment_repositories) {

    private var _binding: FragmentRepositoriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var  repositoriesAdapter: RepositoriesAdapter
    private val viewModel:RepoViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoriesBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRV()
    }
    private fun setRV(){
        repositoriesAdapter= RepositoriesAdapter()
        binding.repo.apply {
            layoutManager=GridLayoutManager(activity,1)
            setHasFixedSize(true)
            adapter=repositoriesAdapter
        }
        viewModel.repoResponse.observe(requireActivity()) { result ->
            repositoriesAdapter.repos=result
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}