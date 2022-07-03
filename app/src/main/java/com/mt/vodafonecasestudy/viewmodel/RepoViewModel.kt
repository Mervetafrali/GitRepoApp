package com.mt.vodafonecasestudy.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mt.vodafonecasestudy.helper.Constants
import com.mt.vodafonecasestudy.model.ReposItem
import com.mt.vodafonecasestudy.model.RepositoriesItem
import com.mt.vodafonecasestudy.model.Users
import com.mt.vodafonecasestudy.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.http.Url
import java.net.URLEncoder
import javax.inject.Inject


@HiltViewModel
class RepoViewModel
@Inject
constructor(private val repository: ApiRepository) : ViewModel() {
    private val _response = MutableLiveData<List<RepositoriesItem>>()
    val repoResponse: LiveData<List<RepositoriesItem>>
        get() = _response
    private val _responseUser = MutableLiveData<Users>()
    val userResponse: LiveData<Users>
        get() = _responseUser
    private val _responseRepos = MutableLiveData<List<ReposItem>>()
    val reposResponse: LiveData<List<ReposItem>>
        get() = _responseRepos

    fun getRepoUrl(url:String){
        getRepos(url)
    }
    fun getUserUrl(url:String){
        getUser(url)
    }

    init{
        getRepository()
    }


    private fun getRepository() = viewModelScope.launch {
        repository.getRepository().let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("Response", "getRepository: ${response.code()}")
            }
        }
    }

    private fun getUser(@Url url:String) = viewModelScope.launch {
        repository.getUser(url).let { response ->
            if (response.isSuccessful) {
                _responseUser.postValue(response.body())
            } else {
                Log.d("Response", "getUser: ${response.code()}")
            }
        }
    }

    private fun getRepos(@Url url:String) = viewModelScope.launch {
        repository.getRepos(url).let { response ->
            if (response.isSuccessful) {
                _responseRepos.postValue(response.body())
            } else {
                Log.d("Response", "getRepos: ${response.code()}")
            }
        }
    }
}