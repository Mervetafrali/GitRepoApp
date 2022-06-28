package com.mt.vodafonecasestudy.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mt.vodafonecasestudy.R
import com.mt.vodafonecasestudy.adapter.RepoAdapter
import com.mt.vodafonecasestudy.databinding.FragmentFirstBinding
import com.mt.vodafonecasestudy.viewmodel.RepoViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var  repoAdapter: RepoAdapter
    private val viewModel:RepoViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRV()
    }
    private fun setRV(){
        repoAdapter= RepoAdapter()
        binding.repo.apply {
            layoutManager=GridLayoutManager(activity,2)
            setHasFixedSize(true)
            adapter=repoAdapter
        }
        viewModel.repoResponse.observe(requireActivity()) { result ->
           // Log.i("tag","fragment"+result.toString())
            repoAdapter.repos=result
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}