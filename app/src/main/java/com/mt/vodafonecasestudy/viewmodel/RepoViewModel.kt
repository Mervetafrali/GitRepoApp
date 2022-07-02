package com.mt.vodafonecasestudy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mt.vodafonecasestudy.model.Repos
import com.mt.vodafonecasestudy.model.ReposItem
import com.mt.vodafonecasestudy.model.RepositoriesItem
import com.mt.vodafonecasestudy.model.Users
import com.mt.vodafonecasestudy.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoViewModel
@Inject constructor(private val repository: ApiRepository): ViewModel(){
    private val _response= MutableLiveData<List<RepositoriesItem>>()
    val repoResponse:LiveData<List<RepositoriesItem>>
    get()=_response
    private val _responseUser=MutableLiveData<Users>()
    val userResponse:LiveData<Users>
    get()=_responseUser
    private val _responseRepos=MutableLiveData<List<ReposItem>>()
    val reposResponse:LiveData<List<ReposItem>>
        get()=_responseRepos
    init{
        getRepository()
        getUser()
        getRepos()
    }
    private  fun getRepository()= viewModelScope.launch {
        repository.getRepository().let { response->
            if(response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.d("Response","getRepository: ${response.code()}")
            }
        }
    }
    private fun getUser()= viewModelScope.launch{
        repository.getUser().let { response->
            if(response.isSuccessful){
                _responseUser.postValue(response.body())
            }else{
                Log.d("Response","getUser: ${response.code()}")
            }
        }
    }
    private fun getRepos()= viewModelScope.launch{
        repository.getRepos().let { response->
            if(response.isSuccessful){
                _responseRepos.postValue(response.body())
            }else{
                Log.d("Response","getUser: ${response.code()}")
            }
        }
    }
}