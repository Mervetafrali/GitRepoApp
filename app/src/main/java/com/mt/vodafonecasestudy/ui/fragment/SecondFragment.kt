package com.mt.vodafonecasestudy.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mt.vodafonecasestudy.R
import com.mt.vodafonecasestudy.databinding.FragmentSecondBinding
import com.mt.vodafonecasestudy.model.RepositoriesItem
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

class SecondFragment : Fragment(R.layout.fragment_second) {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val args: SecondFragmentArgs by navArgs()
    private lateinit var repo:RepositoriesItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(
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
        binding.apply {
            binding.repoNameText.text=repo.full_name
            binding.ownerUsernameText.text=repo.name
            binding.forkCount.text=repo.full_name
            binding.languageText.text=repo.full_name
            binding.dfBrancNameText.text=repo.full_name
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}