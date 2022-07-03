package com.mt.vodafonecasestudy.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.mt.vodafonecasestudy.R
import com.mt.vodafonecasestudy.adapter.ReposAdapter
import com.mt.vodafonecasestudy.databinding.FragmentUserBinding
import com.mt.vodafonecasestudy.helper.Constants
import com.mt.vodafonecasestudy.viewmodel.RepoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment(R.layout.fragment_user) {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val args:  UserFragmentArgs by navArgs()
    private lateinit var reposAdapter: ReposAdapter
    private val viewModel: RepoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name=args.userName
        setRV(name)
    }

    private fun setRV(name:String) {
        reposAdapter = ReposAdapter()
        val urlUser= Constants.BASE_URL+"users/"+name

        binding.repos.apply {
            layoutManager = GridLayoutManager(activity, 1)
            setHasFixedSize(true)
            adapter = reposAdapter
        }
        viewModel.getUserUrl(urlUser)
        val urlRepo = Constants.BASE_URL+"users/"+name+"/repos"
        viewModel.getRepoUrl(urlRepo)
        viewModel.reposResponse.observe(requireActivity()) { result ->
            reposAdapter.repoDetail = result
        }
        viewModel.userResponse.observe(requireActivity()) { user ->
            binding.name.text = user.name
            binding.mail.text = user.email.toString()
            binding.imageView2.load(user.avatar_url) {
                crossfade(true)
                crossfade(2000)
            }
        }


    }

}