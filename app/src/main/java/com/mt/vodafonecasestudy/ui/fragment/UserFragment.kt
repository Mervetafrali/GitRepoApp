package com.mt.vodafonecasestudy.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mt.vodafonecasestudy.R
import com.mt.vodafonecasestudy.adapter.ReposAdapter
import com.mt.vodafonecasestudy.databinding.FragmentRepoDetailBinding
import com.mt.vodafonecasestudy.databinding.FragmentUserBinding
import com.mt.vodafonecasestudy.model.RepositoriesItem
import com.mt.vodafonecasestudy.model.Users
import com.mt.vodafonecasestudy.viewmodel.RepoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment(R.layout.fragment_user) {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private lateinit var user: Users
    private val viewModel: RepoViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRV()
    }
    private fun setRV(){
        viewModel.userResponse.observe(requireActivity()){user->
            binding.text.text=user.name
        }
    }

}